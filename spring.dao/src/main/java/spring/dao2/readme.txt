此包主要功能是熟悉spring4 jdbc template下的多数据源配置功能

使用DynamicDataSource动态选择DB，不同的DS通过DatabaseContextHolder注册到ThreadLocal中。

DAO使用数据库，通过不同spring注解来制定（不够灵活）

需要在运行之前，就确定了DAO曾使用的DB。


