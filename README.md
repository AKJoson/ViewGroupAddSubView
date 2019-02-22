## 开发的过程中有一些表格或者有规律的数据，需要从后台获取，我们可能无法实现确定好个数，另外列表的形式似乎也满足不了我们的需求。这个时候，我们可能就需要动态的添加。
---
### 我来学习一下，如何往Linealyout里面动态的添加子View。 
-- 关键的点就在于：
1. ViewGroup 使用addView(view)来添加子view。如果需要清除所有的子View,调用removeViews()即可。
2. 添加子View的时候，需要设置点击事件的监听，那么思考：这个监听应该回调到哪里去了？
3. 回答应该是父View的ViewModel,这样，子View的数据本身来自于父ViewModel，在父View的ViewModel里面来进行处理，才是不二之选。
### 下面，我们知道，数据本身是会发生刷新，或者因为一些属性改变而改变的，那么子View已经创建，而又需要改变，应该怎么做了？
1. 我们应该知道的就是如何取出之前添加进去的View了，另外我们取出来应该是binding,因为我们要对这个Binding进行设置属性，所以：
//这样就可以取出了  括号里面是view (view)

        DataBindingUtil.getBinding(linearLayout.getChildAt(i));

2. 比如，一行上，需要显示几个子View，那么我们必须确定一个子View的宽，因此

		mContext.getResource().getDisplayMetrics().widthPixels //可以获得宽，除以个数，即可有得出一个item占用的宽度。
		//一行显示4个
		 ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(layout.getContext().getResources().getDisplayMetrics().widthPixels / 4, ViewGroup.LayoutParams.MATCH_PARENT);
		//ViewGrooup在进行addView的同时，将这个layoutParams带上。
		layout.addView(binding.getRoot(),layoutParams);
