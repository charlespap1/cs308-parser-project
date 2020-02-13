#Simulation Review

We all went with the MVC design but implemented it in a slightly different way. Some decided to deliberately separate all M, V, and C parts into different packages and then put particular subclasses within those. Others only created packages for hierarchies and left standalone classes by themselves. We all agreed that more package separation is the best route when organizing your project.

Due to the various parts working together, there were dependencies in which we had to create objects and use them across different parts of the MVC. The best example of this was the Cell class. The Cell class had to be used in all parts--the model, visual and controller--and as a result, a change to the Cell class resulted in many possible instances for fatal dependencies. We all are interested in the idea that an interface or API could be the answer to this question of fatal dependency. 

Common across most of our projects were the creation of an Abstract Cell and Grid hierarchy. We all found that these inheritance flows were necessary to account for new additions to our program and to make it more flexible. The instances in which we did not create a hierarchy were the time we could not account for or extend new features. For example, one person’s group decided to use Rectangle objects to create shaped instead of a more flexible object. 

