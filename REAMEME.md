## scala implementation of delta debugging

实现了论文中的delta debugging算法

论文https://www.cs.purdue.edu/homes/xyzhang/fall07/Papers/delta-debugging.pdf

### demo
假设在parse很长的一串字符串时出现了BUG，用delta debugging可以剔除掉不相关的字符串，留下导致BUG出现的部分字符串。

无法parse的字符串是"154213"
只要字符串中包含"42"，就无法parse
```
def test1(s: String): TestResult =
      if (s.contains("42")) Failed
      else Passed
```
使用delta debugging进行剔除，剩下"42"
```
val res1 = ddmin("154213", test1)
res1 shouldEqual "42"
```

如果导致无法parse的原因是字符串中包含"4"并且包含"3"
```
def test2(s: String): TestResult =
      if (s.contains("4") && s.contains("3")) Failed
      else Passed
```

使用delta debugging进行剔除，剩下"43"
```
val res2 = ddmin("154213", test2)
res1 shouldEqual "43"
```

### motivation
在做分布式系统的测试的时候，BUG出现的时候trace可能非常的长(很多的event)，要在这些event中找出导致bug发生的event，可以使用delta debugging

### TODO
- 用户可定义delta如何(de)compose
- 可定义什么是atomic的delta
- 可定义apply方法: delta如何应用到某个subject上，例如demo中
```
apply(delta: String, subject: String) = delta + subject
```

### how to contribute
please share any thought or code