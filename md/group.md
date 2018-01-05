### [列表分组和粘性头部](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/group)
**GroupItemDecoration**（教程博客：[从零开始实现RecyclerView分组及粘性头部效果](https://juejin.im/post/5a4551ce51882512d82305cb)）

![](https://user-gold-cdn.xitu.io/2017/12/29/160a15f16236cba8?w=208&h=300&f=gif&s=162696)

**SideGroupItemDecoration**

![](http://upload-images.jianshu.io/upload_images/4909537-e56a2587f6d07cee.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 如何使用
**GroupItemDecoration（SideGroupItemDecoration）**目前只支持**LinearLayoutManager.VERTICAL**类型，使用流程如下
* 拷贝[package com.anlia.library.group](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/group)路径下的文件到你的项目中
* **inflate**你写好的布局
```java
LayoutInflater layoutInflater = LayoutInflater.from(this);
View groupView = layoutInflater.inflate(R.layout.item_group,null);
```
* 调用**recyclerView.addItemDecoration**添加**GroupItemDecoration**或**SideGroupItemDecoration**
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