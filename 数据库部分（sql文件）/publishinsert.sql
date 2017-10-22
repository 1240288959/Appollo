insert into press(id,name,url) values ('99924e92-4257-4cf2-8f76-8a1546d9963b','清华大学出版社','http://www.tup.tsinghua.edu.cn/index.html');
insert into press(id,name,url) values ('5b9bef3d-cc5f-45db-ad34-12222c1ff6dd','高等教育出版社','http://www.hep.edu.cn/');
insert into press(id,name,url) values ('ee620606-2f32-4ea2-a735-e211af3d55a7','浙江大学出版社','http://www.press.zju.edu.cn/default.html');
insert into press(id,name,url) values ('a2726c28-b55c-4f7c-b442-55d684707e8a','机械大学出版社','http://www.cmpedu.com/');
insert into press(id,name,url) values ('755443d7-a100-482a-84ef-99c4910e7009','大象出版社','http://www.daxiang.cn/');
insert into press(id,name,url) values ('a64d9f05-09df-4c34-b454-a01ff9034448','人民教育出版社','http://www.pep.com.cn/');
insert into press(id,name,url) values ('709de3bc-9da6-4be1-8207-b6a73e03468a','人民邮电出版社','http://www.ptpress.com.cn/');
insert into press(id,name,url) values ('153c377b-b548-4d4e-837a-1f8e73c051a2','人民文学出版社','http://www.rw-cn.com/');
insert into press(id,name,url) values ('8ac7ceb2-a7ca-44e3-bffa-66f5a3aeb250','中华书局','http://www.zhbc.com.cn/zhsj/fg/home/home.html');
insert into press(id,name,url) values ('42da2e3c-02ec-492f-96d0-6142b0acc281','商务印书馆','http://www.cp.com.cn/');
insert into press(id,name,url) values ('ec117d36-96f5-4d8e-ab0e-c8b06249b2ae','科学出版社','http://www.sciencep.com/');
insert into press(id,name,url) values ('4956023d-1afe-4df5-9ba8-2dc3942b4257','北京大学出版社','http://www.pup.cn/main/');
insert into press(id,name,url) values ('9bc30d9c-e785-4a28-80f9-b06f56270540','中国人民大学出版社','http://www.crup.com.cn/');
insert into press(id,name,url) values ('34cf159c-dc29-4833-a7ff-a08846d8f58b','中国作家出版社','http://www.zgzjcbs.com/');
insert into press(id,name,url) values ('f9ce79b7-40af-4b06-a066-35000b878478','复旦大学出版社','http://edu.fudanpress.com/');
commit;


select name from press where name like '_民%'
