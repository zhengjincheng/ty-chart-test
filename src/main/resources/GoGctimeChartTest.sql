delete from NL_VM_GO_GC where vm_id=422;
INSERT INTO NL_VM_GO_GC (`timestamp`, `vm_id`, `gc_time_total`, `gc_time_max`, `gc_time_min`, `gc_time_squaresum`, `gc_count_total`, `count`) VALUES (floor(UNIX_TIMESTAMP(now())/60)-22616640-10, '422', '3', '2', '0', '5', '0', '2');
