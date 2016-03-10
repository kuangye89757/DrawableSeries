详见G:\android_studio_jikexueyuan\DrawableSeries
================================================================================================================
	第6章 Android的Drawable
		* 非图片类型的Drawable占用空间较小,对减小apk有所帮助
		* 内部宽高通过getIntrinsicWidth和getIntrinsicHeight获取;
				如图片形成的Drawable内部宽高就是图片宽高
				如颜色形成的Drawable没有大小概念
				如背景形成的Drawable内部宽高就是View的大小
		* Drawable的实际区域大小可以通过它的getBounds方法得到,同View相同		
				
				
		一、BitmapDrawable -- 表示一张图片
				<?xml version="1.0" encoding="utf-8"?>
					<bitmap
						xmlns:android="http://schemas.android.com/apk/res/android"
						android:src="@mipmap/ic_launcher"					--图片资源id
						android:antialias="true"							--开启抗锯齿功能
						android:dither="true"								--开启抖动效果,当图片像素和手机像素不一致时,不会过于失真
						android:filter="true"								--开启过滤效果,当图片被拉伸或压缩时,有很好的显示效果
						android:gravity="center_horizontal|center_vertical"	--在容器中的位置
						android:mipMap="false"								--纹理映射一般为false
						android:tileMode="clamp"							--平铺模式:disabled (默认不平铺)
																					   clamp	(四周扩散)
																					   repeat	(水平和竖直平铺)	
																					   mirror	(镜面投影)
						/>


		二、ShapeDrawable -- 通过颜色构造图形		
		
			<?xml version="1.0" encoding="utf-8"?>
				<shape
					xmlns:android="http://schemas.android.com/apk/res/android"
					android:shape="oval">										--图片形状:rectangle(矩形) oval(椭圆) ring(环形) line(线性)

					<!--四个角角度,其中radius表示整个4角优先级最低-->
					<corners
						android:bottomLeftRadius="10dp"
						android:bottomRightRadius="15dp"
						android:radius="45dp"
						android:topLeftRadius="20dp"
						android:topRightRadius="35dp"
						/>

					<!--渐变-->
					<gradient
						android:angle="45"										--渐变角度,只能是45的倍数 0从左到右 90从上到下
						android:centerColor="#333333"
						android:centerX="35dp"									--中心点横坐标	
						android:centerY="35dp"									--中心点纵坐标	
						android:endColor="#666666"								--渐变结束色
						android:gradientRadius="2dp"							--渐变半径
						android:startColor="#e21313"							--渐变开始色
						android:type="linear"									--渐变类型:线性渐变(line) radial(径向渐变) sweep(描线渐变)
						android:useLevel="false"								--一般为false
						/>

					<!--留白-->
					<padding
						android:bottom="2dp"
						android:left="2dp"
						android:right="2dp"
						android:top="2dp"/>

					<!-- shape宽高-->	
					<size														--这样getIntrinsicWidth和getIntrinsicHeight获取后就不是-1了
						android:width="66dp"
						android:height="70dp"/>

					<!-- 纯色填充 -->
					<solid android:color="#454545"/>
					
					<!--描边-->
					<stroke
						android:width="1dp"										--边缘线宽度
						android:color="#545454"									--边缘线颜色
						android:dashGap="1dp"									--虚线间隔
						android:dashWidth="2dp"/>								--虚线宽度		
				</shape>


		三、LayerDrawable -- Drawable集合叠加效果
		
			<?xml version="1.0" encoding="utf-8"?>
				<layer-list xmlns:android="http://schemas.android.com/apk/res/android">		
					<item																	--每个item都能放一个Drawable 左上右下代表相对于View的偏移
						android:bottom="1dp"
						android:left="1dp"
						android:right="1dp"
						>
						<shape android:shape="rectangle">
							<solid android:color="#ffffff"/>
						</shape>
					</item>

				</layer-list>
		
		四、StateListDrawable -- Drawable集合,每个Drawable对应View的一个状态
			
			<?xml version="1.0" encoding="utf-8"?>
				<selector xmlns:android="http://schemas.android.com/apk/res/android"
						  android:constantSize="true"												--true :它的固有大小是内部所有Drawable固有大小的最大值,且保持不变
																									--false:它的固有大小是随状态改变 默认false		
						  android:dither="true"
						  android:variablePadding="false">											--推荐false

					<item android:drawable="@drawable/activity_checkbox_red" android:state_pressed="true"/>		--按下
					<item android:drawable="@drawable/activity_checkbox_red" android:state_focused="true"/>		--焦点
					<item android:drawable="@drawable/activity_checkbox_red" android:state_selected="true"/>	--选中	
					<item android:drawable="@drawable/activity_checkbox_red" android:state_checked="true"/>		--用于Checkbox选中
					<item android:drawable="@drawable/activity_checkbox_red" android:state_enabled="true"/>		--可用时
					<item android:drawable="@drawable/activity_checkbox_white"/>								--默认 由于顺序执行,故放在最后
				</selector>
				
				
		五、InsetDrawable -- 将其他Drawable嵌入自身
		
			<?xml version="1.0" encoding="utf-8"?>
				<inset xmlns:android="http://schemas.android.com/apk/res/android"
					   android:insetBottom="24dp"													--内凹左上右下大小
					   android:insetLeft="12dp"
					   android:insetRight="124dp"
					   android:insetTop="12dp"
					   android:drawable="@drawable/shape_drawable"
					/>


		六、ScaleDrawable -- 根据等级缩放Drawable 0不显示 10000最大
				res/drawable/scale_drawable.xml
				<?xml version="1.0" encoding="utf-8"?>
					<scale xmlns:android="http://schemas.android.com/apk/res/android"
						   android:drawable="@drawable/shape_drawable"
						   android:scaleGravity="center"
						   android:scaleHeight="70%"
						   android:scaleWidth="70%"
						/>
						
				<!--ScaleDrawable的使用-->
				<TextView
					android:id="@+id/test_scale"
					android:layout_width="match_parent"
					android:layout_height="wrap_content"
					android:background="@drawable/scale_drawable"
					/>		
					
				test_scale = (TextView) findViewById(R.id.test_scale);
				ScaleDrawable scaleDrawable = (ScaleDrawable) test_scale.getBackground();
				scaleDrawable.setLevel(1);	

		七、TransitionDrawable --两个Drawable间淡入淡出效果
		
				<?xml version="1.0" encoding="utf-8"?>
				<transition xmlns:android="http://schemas.android.com/apk/res/android">
					<item android:drawable="@drawable/activity_checkbox_white" />
					<item android:drawable="@drawable/activity_checkbox_red" />
				</transition>
				
				 <!--TransitionDrawable的使用-->
				<TextView
					android:id="@+id/test_transition"
					android:layout_width="match_parent"
					android:layout_height="wrap_content"
					android:background="@drawable/transition_drawable"
					/>
					
				test_transition = (TextView) findViewById(R.id.test_transition);
				TransitionDrawable drawable = (TransitionDrawable) test_transition.getBackground();
				drawable.startTransition(3000);	
				
		八、ClipDrawable --根据等级剪切Drawable 	
				res/drawable/clip_drawable.xml
				<?xml version="1.0" encoding="utf-8"?>
				<clip xmlns:android="http://schemas.android.com/apk/res/android"
					  android:clipOrientation="vertical"							--裁切方向 vertical竖直  horizontal横向
					  android:drawable="@mipmap/ic_launcher"
					  android:gravity="bottom"										--裁切方向 vertical竖直:
																							top -- 底部裁切  bottom -- 顶部裁切 center_vertical  --上下同时裁切
																					  裁切方向 horizontal横向:		
					/>																		left-- 右端裁切  right  -- 左端裁切 center_horizontal--左右同时裁切
		
				<!--ClipDrawable的使用-->
				<ImageView
					android:id="@+id/test_clip"
					android:layout_width="wrap_content"
					android:layout_height="wrap_content"
					android:background="@drawable/clip_drawable"
					/>
		
				test_clip = (ImageView) findViewById(R.id.test_clip);
				ClipDrawable clipDrawable = (ClipDrawable) test_clip.getBackground();
				clipDrawable.setLevel(8000);	//0完全裁切 10000不裁切