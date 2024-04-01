## 根据源码学习记录
  核心自动装配就是用的和spi类似的机制原理，关键部分，它负责从应用程序的类路径中识别所有可能的自动配置类。这个方法通常会查找**META-INF/spring.factories**文件中与自动配置相关的条目。

  `
  public String[] selectImports(AnnotationMetadata annotationMetadata) {

    // 1. 检查自动配置是否被启用

     if (!isEnabled(annotationMetadata)) {
        return NO_IMPORTS;
      }

    

    // 2. 加载自动配置的元数据
    AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader
            .loadMetadata(this.beanClassLoader);
    
    // 3. 获取当前注解的属性
    AnnotationAttributes attributes = getAttributes(annotationMetadata);
    
    // 4. 获取候选的自动配置类
    List<String> configurations = getCandidateConfigurations(annotationMetadata,
            attributes);
    
    // 5. 移除重复的配置类
    configurations = removeDuplicates(configurations);
    
    // 6. 获取需要排除的自动配置类
    Set<String> exclusions = getExclusions(annotationMetadata, attributes);
    
    // 7. 检查并排除不需要的自动配置类
    checkExcludedClasses(configurations, exclusions);
    configurations.removeAll(exclusions);
    
    // 8. 应用过滤规则，可能是基于条件的过滤
    configurations = filter(configurations, autoConfigurationMetadata);
    
    // 9. 触发自动配置导入相关的事件
    fireAutoConfigurationImportEvents(configurations, exclusions);
    
    // 10. 将配置类列表转换为字符串数组并返回
    return StringUtils.toStringArray(configurations);
  }`

### 还有一个核心就是自动获取候选的配置属性 **getCandidateConfigurations(annotationMetadata,attributes)**

 * getCandidateConfigurations方法使用**SpringFactoriesLoader.loadFactoryNames**方法来加载META-INF/spring.factories文件中定义的所有自动配置类的名称。这个方法接受两个参数：一个是用于查找工厂名称的类（在这种情况下是自动配置类），另一个是类加载器。
   
`
  protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
   // 使用SpringFactoriesLoader加载META-INF/spring.factories文件中的所有自动配置类
   List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader());
   Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you " +
   "are using a custom packaging, make sure that file is correct.");
   return configurations;
   }`

#### ??? 那获取候选的配置属性 getCandidateConfigurations 是怎么识别出**哪些配置要获取**？？ 
   * 我们现在知道是 ****AutoConfiguration 结尾的 ，怎么做到的
   * 那我们就来看 spring.factories 文件
   * **键**通常是org.springframework.boot.autoconfigure.EnableAutoConfiguration，这是@EnableAutoConfiguration注解的全限定类名。与这个**键**关联的**值**是一**系列自动配置类的全限定名**，这些类实现了特定的自动配置逻辑。
      文件示例：
    ` org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
     com.example.AutoConfiguration1,\
     com.example.AutoConfiguration2,\
     com.example.AutoConfiguration3`
   * 键就是： org.springframework.boot.autoconfigure.EnableAutoConfiguration
   *值就是一个list<String> 对应配置类的全限定名：com.example.AutoConfiguration1 ，com.example.AutoConfiguration2 ，com.example.AutoConfiguration3
   * 知道权限定名了，我就可以获取Class对象 也可以注入bean了 
#### SpringFactoriesLoader 中使用的类加载器？？
* 在Spring Boot应用中，loadFactoryNames方法通常会使用**当前线程的上下文类加载器（Thread.currentThread().getContextClassLoader()）**或者Spring应用上下文的类加载器（通过ApplicationContext.getClassLoader()获取）。这些类加载器能够访问**应用打包时包含的所有资源**，包括嵌入的JAR文件和类。
* 当SpringFactoriesLoader.loadFactoryNames方法被调用时，它会使用提供的类加载器来搜索所有可访问的META-INF/spring.factories文件，并从这些文件中加载指定类（如EnableAutoConfiguration.class）关联的工厂名称。
* 这个类加载器参数的存在允许SpringFactoriesLoader在不同的环境中灵活地工作，例如在标准的Java应用、Web应用或者OSGi环境中，这些环境可能会有不同的类加载器层次结构和类路径配置。通过传递正确的类加载器，SpringFactoriesLoader能够确保它能够正确地找到并加载所需的资源。

#### 自动装配怎么处理手动定义覆盖或者排除的系统默认的配置的？
 * 排除
   * **@SpringBootApplication**注解的排除属性
   * **@EnableAutoConfiguration**注解的排除属性
   *  **application.properties** spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * 覆盖
   
