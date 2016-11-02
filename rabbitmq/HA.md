和exchange，binding不同，在所有的node上面都存在，queue默认只在存在一个node中。 Queue可以配置为Mirror的。
每个mirrored queue都由一个master，多个slave构成，当掉线后，由最老当node充当master。

发送到master的消息会同步到所有到slave上面。Consumer都是连接到master上面，不管它是连接到哪个node。Queue的mirror提高了可用性，但是并没有分散压力。

queue使用policy来启动mirroring

|ha-mode|ha-params|result|
|-|-|-|
|all|none|queue复制到所有的node上面。当一个新的node加入到cluster之中时，queue也会同步到其中。|
|exactly|count|Queue复制到count数量到node上。如果cluster中的node数量比count少，复制到所有到node。如果一个mirror node下线，会添加个新到mirror node。|
|nodes|node names|Queue被复制到一个node names列表到node中。node name通常是rabbit@hostname到方式。|

所有到queue操作都是先在master上发生，然后复制到slave上。这保证来消息到FIFO。

master queue可以分布在不同到node上面，有以下三种策略。
1. min-masters，选择最少 master queue的node
2. client-local 选择client连接的node
3. radom 随机


如果node policy有变化，导致当前master不在当前的policy中，为了保证消息不丢失，RabbitMQ会保持当前master直到另外的slave已经同步了。

Exclusive queues
