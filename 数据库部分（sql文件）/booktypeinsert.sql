insert into booktype(id,name,parent_id) values('2bcdcf2f-1787-44cb-a39f-9d666bf5f4c6','С˵������',null);
insert into booktype(id,name,parent_id) values('2242ea7a-8860-4840-98c1-d6ce8936d741','ħ��','2bcdcf2f-1787-44cb-a39f-9d666bf5f4c6');
insert into booktype(id,name,parent_id) values('f866b852-3e70-4842-99ba-4919b11d9123','С˵','2bcdcf2f-1787-44cb-a39f-9d666bf5f4c6');
insert into booktype(id,name,parent_id) values('7a83e85b-dd80-447f-94b4-e087b4c860cd','��ѧ','2bcdcf2f-1787-44cb-a39f-9d666bf5f4c6');
insert into booktype(id,name,parent_id) values('92607e72-3bf2-4fbb-91e6-5f4df1d25488','����','2bcdcf2f-1787-44cb-a39f-9d666bf5f4c6');

insert into booktype(id,name,parent_id) values('530e8f86-8143-44f2-bebe-24a3cd9cbddd','�ճ�������',null);
insert into booktype(id,name,parent_id) values('d1271418-5429-4a35-bc77-db3ae1cddbbe','ʱ��','530e8f86-8143-44f2-bebe-24a3cd9cbddd');
insert into booktype(id,name,parent_id) values('fa59584d-b22c-4d95-a84c-ff3ca47aeadf','����','530e8f86-8143-44f2-bebe-24a3cd9cbddd');
insert into booktype(id,name,parent_id) values('ee0af977-8d28-427f-a521-80431323e200','�Ҿ�','530e8f86-8143-44f2-bebe-24a3cd9cbddd');
insert into booktype(id,name,parent_id) values('5e464515-87b6-4e5a-8f70-677ae16b2aca','ʳ��','530e8f86-8143-44f2-bebe-24a3cd9cbddd');

insert into booktype(id,name,parent_id) values('5ae6b948-99d1-41c6-affa-bdfce4790584','������',null);
insert into booktype(id,name,parent_id) values('e1df4a98-90ce-4761-bd05-95dffdb44c98','�̲�','5ae6b948-99d1-41c6-affa-bdfce4790584');
insert into booktype(id,name,parent_id) values('1590fa17-6fee-4f2a-a568-87ecd69d7773','�̸�','5ae6b948-99d1-41c6-affa-bdfce4790584');
insert into booktype(id,name,parent_id) values('14b3a31c-89bb-486d-8ef1-c555b5c57f68','����','5ae6b948-99d1-41c6-affa-bdfce4790584');
insert into booktype(id,name,parent_id) values('5006b433-9809-400e-b3de-7d2e9c762516','����','5ae6b948-99d1-41c6-affa-bdfce4790584');

insert into booktype(id,name,parent_id) values('5ab1e211-052e-440e-b56e-1c07e3652205','���������',null);
insert into booktype(id,name,parent_id) values('6d6291c3-6228-49cf-a74a-cbc23a44e4d5','����','5ab1e211-052e-440e-b56e-1c07e3652205');
insert into booktype(id,name,parent_id) values('0f72eb16-d108-4959-b444-6b218fd64745','����','5ab1e211-052e-440e-b56e-1c07e3652205');
insert into booktype(id,name,parent_id) values('58c381bc-529d-49d1-8e5c-07484c63c822','���','5ab1e211-052e-440e-b56e-1c07e3652205');
insert into booktype(id,name,parent_id) values('34a902f5-015d-4db8-9afc-707a08e151db','����','5ab1e211-052e-440e-b56e-1c07e3652205');

commit;

select * from booktype
select * from booktype where name like '%��' and id like '5%';
select * from booktype where name like '%��' or id like '5%';

