

RAM node把metadata保存在内存里面，不需要去读写磁盘，所有性能会更好一点。但是对于可持久化的queue的数据总是保存在磁盘上的，所以RAM node只会提高
resouce管理的速度(添加或者删除queue， exchange，vhost等)
