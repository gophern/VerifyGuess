package study.math.probability;

public class TuesdayBoy {
	String[] genders = new String[] { "女", "男" };
	// { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	String[] weeks = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	private void generateSQL() {
		System.out.println("-- 创建Table");
		//@formatter:off
		System.out.println("CREATE TABLE TuesdayBoy (\n" + 
		"		id int(11) NOT NULL AUTO_INCREMENT,\n" + 
		"		c1gender varchar(50) DEFAULT NULL,\n" + 
		"		c1week varchar(50) DEFAULT NULL,\n" + 
		"		c2gender varchar(50) DEFAULT NULL,\n" + 
		"		c2week varchar(50) DEFAULT NULL,\n" + 
		"		PRIMARY KEY (id);\n\n");
		//@formatter:on

		System.out.println("-- 插入数据");
		for (int c1g = 0; c1g < 2; c1g++)
			for (int c1w = 0; c1w < 7; c1w++)
				for (int c2g = 0; c2g < 2; c2g++)
					for (int c2w = 0; c2w < 7; c2w++) {
						String row = String.format(
								"INSERT INTO TuesdayBoy (c1gender, c1week, c2gender, c2week) VALUES ('%s', '%s', '%s', '%s');",
								genders[c1g], weeks[c1w], genders[c2g], weeks[c2w]);
						System.out.println(row);
					}

		System.out.println("\n\n-- 验证");

		//@formatter:off
		System.out.println(
				"SELECT t.Total, b.Bingo, concat(b.Bingo, '/', t.Total) 答案, (b.Bingo / t.Total) as 计算结果 from\n" + 
				"(\n" + 
				"	-- 总样本数（满足：至少有一个是周二的男孩）\n" + 
				"	SELECT count(*) as Total FROM TuesdayBoy\n" + 
				"	WHERE (c1gender='男' and c1week='星期二')\n" + 
				"	  OR (c2gender='男' and c2week='星期二')\n" + 
				") t,\n" + 
				"(\n" + 
				"	-- 至少有一个是周二的男孩 AND 2个都是男孩\n" + 
				"	SELECT count(*) as Bingo FROM TuesdayBoy\n" + 
				"	WHERE ((c1gender='男' and c1week='星期二') and (c2gender='男'))\n" + 
				"	  OR ((c2gender='男' and c2week='星期二') and (c1gender='男'))\n" + 
				") b;\n");
		//@formatter:on

		System.out.println("\n-- 实测结果是：");
		System.out.println("-- # Total, Bingo, 答案, 计算结果");
		System.out.println("-- 27, 13, 13/27, 0.4815");
	}

	public static void main(String[] args) {
		(new TuesdayBoy()).generateSQL();
	}
}
