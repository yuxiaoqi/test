## 源码分析

#### 我看了 size isEmpty mappingCount三个方法底层调用都是sumCount方法 三者性能上没有区别呀？

 **基础**

你的提问非常精准，你说的没错，size(), isEmpty() 和 mappingCount() 这三个函数都是调用 sumCount() 来获取元素数量。所以从**调用路径**来看，它们的**性能应该是相当**的。并且，实际上，ConcurrentHashMap 的这些方法都是设计为尽可能高效的

* 然而，这三个方法所代表的语义却有些不同，具体使用哪个方法取决于你对精度的需求和你的**逻辑需求**：
1. size() 返回的是一个 int，如果元素数量**超过 Integer.MAX_VALUE，它会返回 Integer.MAX_VALUE**。所以如果你的元素数量可能超过这个值，那么 size() 方法可能无法满足你的需求。
2. isEmpty() 方法用来判断集合是否为空，即是否无元素。这个语义很清晰，不要求知道元素的确切数量，只需要知道是否有元素存在。
3. mappingCount() 返回元素数量的 long 值，如果你需要一个**能精确反映元素数量的值**（尤其是**元素数量可能超过 Integer.MAX_VALUE**），那么你应该使用 mappingCount()。

* 所以，没有一个是无条件优于其他的，具体应用哪个，要看你的具体需求。贸然的误用可能会带来不必要的性能损耗。因此，即使这三个方法的内部实现几乎相同，也应对其适当的使用进行适当的规划和考虑。


#### size的数量是如何维护的
**基础**

* 在ConcurrentHashMap中，元素的数量通过两个字段来维护的：**baseCount和counterCells**。了解这两个字段的作用，可以更好的理解 size 以及其他数量相关的方法是如何工作的。
* baseCount：这是一个long类型的字段，用于计算ConcurrentHashMap中元素的总数。
* counterCells：这是一个CounterCell类型的数组，其中每个CounterCell包含一个value字段，这个value也是用于计算元素的总数的。

当添加或删除元素时，ConcurrentHashMap会通过以下方式来更新元素数量：
1. 首先，try to add or subtract from baseCount 使用的是cas操作 基于乐观操作 直接在baseCount上操作。
2. 如果成功，则无需进一步操作。这通常在并发等级较低时发生。
3. 如果失败，ConcurrentHashMap将尝试在**counterCells数组**中找到一个元素，并在该元素上进行CAS操作来增加或减少值。
4. 如果counterCells数组中没有空闲的元素，或者CAS操作失败了很多次，那么ConcurrentHashMap会进行元胞扩张，增加counterCells数组的大小。
* 因此，通过这种方式，ConcurrentHashMap能够在不使用全局锁的情况下，以线程安全的方式维护其元素的数量。并且，由于其设计，这个数量**计算过程是非阻塞的**，也就是说，调用 size() 或相关方法并不会阻止其他线程对Map的修改操作。但是，由于可能存在并发修改，size() 方法返回的结果应被**视为近似值**。