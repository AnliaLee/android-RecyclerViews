### [顶部投影效果](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/header)

**TopProjectionDecoration**

![](http://upload-images.jianshu.io/upload_images/4909537-e3eb374299e1fa55.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 如何使用
**TopProjectionDecoration**目前只支持**LinearLayoutManager.VERTICAL**类型，使用流程如下
* 拷贝[package com.anlia.library.header](https://github.com/AnliaLee/android-RecyclerViews/tree/master/library/src/main/java/com/anlia/library/header)路径下的文件到你的项目中
* **inflate**你写好的布局
```java
LayoutInflater layoutInflater = LayoutInflater.from(this);
View groupView = layoutInflater.inflate(R.layout.item_top,null);
```
* 调用**recyclerView.addItemDecoration**添加**TopProjectionDecoration**
```java
TopProjectionDecoration decoration = new TopProjectionDecoration(this, view, new TopProjectionDecoration.DecorationCallback() {
	@Override
	public void buildHeaderView(View headerView, int position) {
		TextView textView = (TextView) headerView.findViewById(R.id.text_name);
		textView.setText(list.get(position));
	}
});
//decoration.isProjectionChange(false);//顶部投影是否随列表滑动而改变
recyclerView.addItemDecoration(decoration);
```
* 监听**HeaderView点击与长按**事件
```java
recyclerView.addOnItemTouchListener(new TopProjectionClickListener(decoration, new TopProjectionClickListener.OnTopProjectionClickListener() {
	@Override
	public void onTopProjectionClick(View headerView, int position) {
		Toast.makeText(TopProjectionActivity.this, "点击了Header，对应position为："+position, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTopProjectionLongClick(View headerView, int position) {
		Toast.makeText(TopProjectionActivity.this, "长按了Header，对应position为："+position, Toast.LENGTH_SHORT).show();
	}
}));
```
***
如果还是不清楚可以去看下demo