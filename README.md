# android-RecyclerViews
#### 版本更新
* 1.1.0：新增监听**RecyclerView Item、GroupItem点击与长按**事件的接口
***
####  [监听RecyclerView Item点击与长按事件](https://github.com/AnliaLee/android-RecyclerViews/blob/master/library/src/main/java/com/anlia/library/base/ItemClickListener.java)
##### 如何使用
* 拷贝[ItemClickListener.java](https://github.com/AnliaLee/android-RecyclerViews/blob/master/library/src/main/java/com/anlia/library/base/ItemClickListener.java)到你的项目中
* 调用**recyclerView.addOnItemTouchListener**添加接口回调
```java
recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new ItemClickListener.OnItemClickListener() {
	@Override
	public void onItemClick(View view, int position) {
	
	}

	@Override
	public void onItemLongClick(View view, int position) {
	
	}
}));
```
**ItemClickListener**暂不支持监听**Item的子控件**，若需要监听**Item子控件**可在**Adapter**中编写相应接口

***

#### [列表分组和粘性头部](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/group)
##### 教程博客：[从零开始实现RecyclerView分组及粘性头部效果](https://juejin.im/post/5a4551ce51882512d82305cb)
##### 效果图
![](https://user-gold-cdn.xitu.io/2017/12/29/160a15f16236cba8?w=208&h=300&f=gif&s=162696)

##### 如何使用
**GroupItemDecoration**目前只支持**LinearLayoutManager.VERTICAL**类型，使用流程如下
* 拷贝[package com.anlia.library.group](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/group)路径下的文件到你的项目中
* **inflate**你写好的布局
```java
LayoutInflater layoutInflater = LayoutInflater.from(this);
View groupView = layoutInflater.inflate(R.layout.item_group,null);
```
* 调用**recyclerView.addItemDecoration**添加**GroupItemDecoration**
```java
GroupItemDecoration groupItemDecoration = new GroupItemDecoration(this, view, new GroupItemDecoration.DecorationCallback() {
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
});
recyclerView.addItemDecoration(groupItemDecoration);
```
* 监听**GroupItem点击与长按**事件
```java
recyclerView.addOnItemTouchListener(new GroupItemClickListener(groupItemDecoration,new GroupItemClickListener.OnGroupItemClickListener() {
	@Override
	public void onGroupItemClick(GroupItem groupItem) {
		Toast.makeText(MainActivity.this, "点击了Group:"+groupItem.getData("name"), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onGroupItemLongClick(GroupItem groupItem) {
		Toast.makeText(MainActivity.this, "长按了Group:"+groupItem.getData("name"), Toast.LENGTH_SHORT).show();
	}
}));
```
***
如果还是不清楚可以去看下demo
