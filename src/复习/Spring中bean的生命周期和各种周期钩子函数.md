## 生命周期概要：
* **实例化（Instantiation）：**
  * 这是生命周期的开始，Spring容器使用Bean的构造器创建Bean实例。如果Bean配置了工厂方法，则使用工厂方法来创建Bean实例。
* **填充属性（Populate Properties）**：
  * Spring容器通过反射机制，将定义在配置文件或注解中的属性值注入到Bean的属性中。
* Bean名称赋值（Set Bean Name）：
  * 如果Bean实现了BeanNameAware接口，Spring容器会调用setBeanName(String name)方法，传入Bean的ID或名称。
  * Bean工厂赋值（Set Bean Factory）：
  * 在这个阶段，Spring容器会检查Bean是否实现了**以下*Aware接口**，并调用相应的方法：
    * BeanNameAware：调用setBeanName(String name)，传入Bean的ID。
    * BeanFactoryAware：调用setBeanFactory(BeanFactory beanFactory)，传入BeanFactory实例。
    *  ApplicationContextAware：调用setApplicationContext(ApplicationContext applicationContext)，传入ApplicationContext实例。
    *  **其他*Aware接口，**如EnvironmentAware, ResourceLoaderAware, MessageSourceAware, ApplicationEventPublisherAware, 等，也会在这个阶段被调用。
  * 如果Bean实现了BeanFactoryAware接口，Spring容器会调用setBeanFactory(BeanFactory beanFactory)方法，传入BeanFactory实例。
* **应用Bean的前置处理器**（Apply Bean Post Processors Before Initialization）：
  * **Spring容器会调用BeanPostProcessor接口的postProcessBeforeInitialization**(Object bean, String beanName)方法。这允许对Bean实例进行额外的处理，比如检查标记接口或者包装Bean实例。
* **初始化（Initialization）**：
  * 如果Bean实现了InitializingBean接口，Spring容器会调用**afterPropertiesSet()**方法。
  * 如果Bean在配置文件中定义了init-method，那么指定的初始化方法也会被调用。
* **应用Bean的后置处理器**（Apply Bean Post Processors After Initialization）：
  * Spring容器会调用BeanPostProcessor接口的**postProcessAfterInitialization**(Object bean, String beanName)方法。这可以用于代理增强Bean，例如通过AOP代理包装Bean。
* **Bean准备就绪（Ready to Use）**：
  * 此时，Bean已经准备好被应用程序使用了。
* **销毁（Destruction）**：如果Bean实现了 DisposableBean 接口，Spring容器会调用destroy()方法。
* **销毁（Destruction）**：如果Bean在配置文件中定义了**destroy-method**，那么指定的销毁方法也会被调用


### 填充属性（Populate Properties） 重要介绍下
### 三层缓存机制 如何解决Spring Bean的循环依赖   
   * 在 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#getEarlyBeanReference 有下面的逻辑
   * 第三极缓存存储的是 ObjectFactory bean的创建工厂 他会根据 BeanPostProcessors 具体来说是： --SmartInstantiationAwareBeanPostProcessor 来判断 早期bean工厂是创建的直接引用还是 代理对象
   * 二级缓存就是在 属性填充的时候，发现了循环依赖需要获取到早期引用 这个时候就会获取到早期引用 同时会将引用
