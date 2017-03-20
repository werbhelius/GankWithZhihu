
# 实践！业余时间做的一款阅读类App （MVP + RxJava + Retrofit）

[APK下载地址](https://fir.im/bew6) 或 [Releases](https://github.com/Werb/GankWithZhihu/releases)

* 整体项目基于 MVP + RxJava + Retrofit
* 通过 Retrofit 实现了无网缓存
* 基于 MVP 模式对 Activity 和 Fragment 封装了两个基类，同样适用于非 MVP 的实现。
* 运用 RecyclerView 加载了多种复杂布局
* 用到了一些很棒的第三方库
* GitHub 项目地址 : [https://github.com/Werb/GankWithZhihu](https://github.com/Werb/GankWithZhihu)


### 闪屏
* 实现了类似于 Twitter 的闪屏动画，中间的小太阳可以扩大到中心，进入主界面
* 闪屏中的字体叫做 old English , 纽约时报专用字体

![splash](https://raw.githubusercontent.com/Werb/GankWithZhihu/master/screenshots/splash.png)

### 主界面
* 汇集了知乎日报，干货集中营，以及好奇心日报，三种不同风格的阅读体验
* 知乎日报 API 取自[ZhihuDailyPurify](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)
* 干货集中营 API 取自[gank.io](http://gank.io/api)
* 好奇心日报 API 是自己爬取所得，后续会整理到 GitHub 上

![main](https://raw.githubusercontent.com/Werb/GankWithZhihu/master/screenshots/zhigan.png)

### 一些有意思的地方
* 知乎日报的详细详细界面，不是采用 webView 加载 url路径实现的，而是根据 api 返回的 html 标签代码，拼接 Css 和 JS 实现的，很有意思，我分析知乎这样做的原因，应该是为了实现在无网状态下，同样可以保持阅读体验
* 各种APi返回的数据格式都很复杂，特别是好奇心日报，不单单是list集合，所以在项目中，使用 RecyclerView 加载了很多复杂布局，特别是实现了如何根据数据来判断布局的加载
* 从开始学习到第一次使用 MVP + RxJava + Retrofit 开发项目，真正体会到了它的方便与强大之处
* 项目中，有很多代码是可以重复利用的，为了更好的体会 MVP 思想，我目前还没有重构，后续会根据功能进行优化
* 目前对内存优化问题，我控制的不是很好，如果有在这方面有经验很擅长的同学，希望可以联系我

![detail](https://raw.githubusercontent.com/Werb/GankWithZhihu/master/screenshots/maindetail.png)

### 很高兴你看到这里

> 有时候啊   你总是在追赶前面的人

> 总是抱怨自己为什么不能再努力一点

>累了你可以停下来   看看原来的自己

>其实你已经很了不起了。

![aboutme](https://raw.githubusercontent.com/Werb/GankWithZhihu/master/screenshots/aboutme.png)

* [业余时间写了一个第三方微博（不使用官方SDK）](https://github.com/Werb/Werb)
* 欢迎 Star 和 Fork


### License
* 感谢开源项目 [ZhihuDailyPurify](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)
* 感谢 [gank.io](http://gank.io/api)
* 感谢 [MeiZhi](https://github.com/drakeet/Meizhi)
* 同时希望可以帮助到其他人
* 项目中用到的 api 所有权归 知乎，gank.io，好奇心日报所有，本项目仅是用来学习使用





            Copyright 2016 Werb

            Licensed under the Apache License, Version 2.0 (the "License");
            you may not use this file except in compliance with the License.
            You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

            Unless required by applicable law or agreed to in writing, software
            distributed under the License is distributed on an "AS IS" BASIS,
            WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
            See the License for the specific language governing permissions and
            limitations under the License.





### Contact Me
* Email: 1025004680@qq.com
* Blog : [Werb's blog](http://werb.github.io/)
* Weibo: [UMR80](http://weibo.com/singerwannber )
* GitHub: [Werb](https://github.com/Werb)
