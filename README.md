# android-RecyclerViews
**RecyclerView**的扩展库，提供各种基于**RecyclerView**的扩展实现方案
#### [列表分组和粘性头部](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/group)
###### 教程博客：[从零开始实现RecyclerView分组及粘性头部效果](https://juejin.im/post/5a4551ce51882512d82305cb)
###### 效果图
![](https://user-gold-cdn.xitu.io/2017/12/29/160a15f16236cba8?w=208&h=300&f=gif&s=162696)

###### 如何使用
**GroupItemDecoration**目前只支持**LinearLayoutManager.VERTICAL**类型，使用流程如下
* 拷贝[package com.anlia.library.group](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/group)路径下的文件到你的项目中
* **inflate**你写好的布局
```java
LayoutInflater layoutInflater = LayoutInflater.from(this);
View groupView = layoutInflater.inflate(R.layout.item_group,null);
```
* 调用**recyclerView.addItemDecoration**添加**GroupItemDecoration**
```java
recyclerView.addItemDecoration(new GroupItemDecoration(this,groupView,new GroupItemDecoration.DecorationCallback() {
	@Override
	public void setGroup(List<GroupItem> groupList) {
		//设置分组，GroupItem(int startPosition)，例如：
		GroupItem groupItem = new GroupItem(0);
		groupItem.setData("name","第1组");
		groupList.add(groupItem);

		groupItem = new GroupItem(5);
		groupItem.setData("name","第2组");
		groupList.add(groupItem);
	}

	@Override
	public void buildGroupView(View groupView, GroupItem groupItem) {
		//构建groupView，通过groupView.findViewById找到内部控件（暂不支持点击事件等），例如
		TextView textName = (TextView) groupView.findViewById(R.id.text_name);
		textName.setText(groupItem.getData("name").toString());
	}
}));
```
如果还是不清楚可以去看下demo
