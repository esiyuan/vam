# vam
能可视化监控的异步任务执行工具，业务系统可以用来做分布式的事务补偿或者异步批处理以及任何异步处理。

## vam组件定义了几个概念，即：任务类型，任务，异常任务
在vam组件中，定义某种业务操作为任务类型，即属于此类型的任务执行相同的业务代码，具体的不同由业务id类区分。
异常任务为任务在规定的最大执行次数后还未执行成功的，将转移到异常中，需要人工的进行补偿。


**下面展示几个页面介绍vam组件**

### 任务类型管理
**列表展示**

![任务类型管理](https://github.com/esiyuan/vam/blob/master/taskTpeList.png)

**任务配置**
![](https://github.com/esiyuan/vam/blob/master/taskType.png)


### 任务管理
![任务管理](https://github.com/esiyuan/vam/blob/master/tasklist.png)

### 异常管理
![任务管理](https://github.com/esiyuan/vam/blob/master/exception.png)


## 如何使用

**组件分2个模块，assignment和backend**

**assignment**负责所有的数据库操作，业务系统插入任务需要引入此包，只需调用TaskInfoFacade.insert(TaskInfoDto record) 
**backend** 主要是页面展示，可以作为独立的项目进行部署，需要依赖assignment模块。

### 具体操作

1. 克隆当前项目： git clone git@github.com:esiyuan/vam.git
2. 找到根目录下的sql，进行建表

3. 部署backend项目 
   需要依赖assignment，此时只需要修改数据源配置dao.properties 

4. 业务系统接入assignment
  修改spring-dao.xml文件数据源，如果系统已配置数据源，则直接删除，引入spring-context.xml 或者在业务系统中扫描路径加入com.vam.task包。
  每种任务操作，业务系统都需要继承AbstraceTaskProcessor，实现springbean，并在任务类型配置页面配置当前任务执行的操作，服务名称就是就是这个bean的名   字。然后业务系统通过TaskInfoFacade.insert(TaskInfoDto record) 插入任务。
5. 业务系统自行以适当的频率调度TimeTriger.timeTrigger(),可以分布式部署，vam系统将进行任务的触发。
   

#### 页面路径
任务类型管理： /tasktype/show.htm
任务管理：    /task/show.htm
异常管理：    /exception/show.htm

