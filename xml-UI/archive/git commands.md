### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is git commands used usually*

---

* 设置git用户的信息
```bash
# list全部属性
git config -l

# 设置当前git用户名及邮件属性，仅作用于当前git仓库
git config user.name "Gene"
git config user.email "Gene@genesis.org"

# 设置全局性git用户名及邮件属性，会作用于全部git仓库
git config --global user.name "Gene"
git config --global user.email "Gene@genesis.org"

# remove global config
git config --global --unset-all user.name
```
* 初始化一个空的本地git仓库
```bash
git init
```
* 直接克隆一个远端代码仓库到本地
```bash
$git clone https://github.com/openstack/glance.git
```
* 在仓库中添加一个远程代码源
```bash
# 在本地创建一个alias为glance的remote仓库(glance.git)映射
git remote add glance https://github.com/openstack/glance.git

# 在本地创建一个alias为Eugene的remote仓库映射
git remote add Eugene https://github.com/toyboxman/incubator-griffin.git

# list当前仓库所有remote代码源
git remote -v
Eugene  https://github.com/toyboxman/incubator-griffin.git (fetch)
Eugene  https://github.com/toyboxman/incubator-griffin.git (push)
glance  https://github.com/openstack/glance.git (fetch)
glance  https://github.com/openstack/glance.git (push)
origin  https://github.com/apache/incubator-griffin.git (fetch)
origin  https://github.com/apache/incubator-griffin.git (push)

# 删除remote代码源映射
git remote remove glance

# 查看远端repo
git remote show Eugene
* remote Eugene
  Fetch URL: https://github.com/toyboxman/incubator-griffin.git
  Push  URL: https://github.com/toyboxman/incubator-griffin.git
  HEAD branch: master
  Remote branches:
    griffin-0.1.5-incubating-rc1                   tracked
    griffin-0.1.5-incubating-rc2                   tracked
    griffin-0.1.5-incubating-rc3                   tracked
    master                                         tracked
    refs/remotes/Eugene/feature/update_measure_pom stale (use 'git remote prune' to remove)
    refs/remotes/Eugene/refactor/testcases         stale (use 'git remote prune' to remove)
    refs/remotes/Eugene/refactor/update-cases      stale (use 'git remote prune' to remove)
  Local refs configured for 'git push':
    griffin-0.1.5-incubating-rc3 pushes to griffin-0.1.5-incubating-rc3 (up to date)
    master                       pushes to master                       (fast-forwardable)

# 清除remote已删除本地还存在的stale分支
git remote prune --dry-run Eugene
git remote prune Eugene
Pruning Eugene
URL: https://github.com/toyboxman/incubator-griffin.git
 * [pruned] Eugene/feature/update_measure_pom
 * [pruned] Eugene/refactor/testcases
 * [pruned] Eugene/refactor/update-cases
```

* 更新代码仓库
```bash
# 更新本地远程代码源,不会覆盖本地分支
git remote update
# 获取远端代码库中最新的代码，并且覆盖本地分支
git pull
```

* 提交代码到远程代码库
```bash
# 把本地当前分支提交到remote映射的分支上
git push

# 把本地glance目录下最新push到remote的icehouse分支
git push glance HEAD:refs/heads/icehouse

# Eugene目录中master分支不存在，推送失败
git push Eugene master:refactor/testcases   
error: src refspec master does not match any.
error: failed to push some refs to 'https://github.com/toyboxman/incubator-griffin.git'
# 推送Eugene目录下m0分支到remote的refactor/testcases分支成功
git push Eugene m0:refactor/testcases
To https://github.com/toyboxman/incubator-griffin.git
 * [new branch]      m0 -> refactor/testcases
```

* 建立一个可供其他机器下载的remote repo
```bash
# 先在remote指定目录中init一个仓库
# 本地创建一个远程git repo映射的alias
git remote add myAppName ssh://192.168.149.128/~/source/app/.git 

# 将本地myAppName目录下master分支推送到remote repo
git push myAppName master
```

* 查看仓库中文件状态
```bash
git status
```
* 在本地分支中添加文件或目录
```bash
# 将source中所有文件子目录都加入待提交staged状态
git add ./source 

# 文字菜单方式查看当前所有待提交文件状态
git add -i  

# 把所有修改文件加入待提交staged状态
git add --all  
```

* 在本地分支中删除文件或目录
```bash
# 将source中所有文件子目录都从待提交staged状态改成untracked状态
git rm --cached ./source
```

* 提交代码变更到本地分支中
```bash
# 提交全部更改的文件
git commit -a 

# 修正本地分支的代码提交
git commit --amend 

# 修正本地分支的代码提交中作者信息
git commit --amend --author="Gene <Gene@genesis.org>" --no-edit
```

* 代码库分支操作
```bash
# 查看当前代码库全部分支
git branch -a  

# 查看本地分支和远程分支关系,包括分支间关联,提交状态对比
git branch -vv

# 强制删除master分支
git branch -D master  

# 删除master分支，但需要所有本地提交已经合并到upstream或者没有变化
git branch -d master  

# 重命名当前分支成 master-dev
git branch -m master-dev

# 查看某个提交存在哪些分支中
git branch --contain e96a53a68b2ed1ce9b98661b07f8071e789d2319
```

* 代码库分支checkout操作
```bash
# 将glance/stable/icehouse分支checkout成一个本地master分支
git checkout master glance/stable/icehouse

# 从当前分支切换到master分支
$git checkout master 

# 临时切换到某个commit位置,切换后会与当前的分支脱离,处于无分支checkout状态
git checkout 0d1d7fc32

# 将当前分支代码回滚到upstream HEAD位置
git checkout -f  

# 丢弃在当前分支中对Run.java文件的修改,此时文件处于未提交状态
git checkout -- Run.java  
```

* 查看当前代码库全部tag快照
```bash
git tag
# 查看如何创建删除标签
$git tag --help  
```

* 把tag对应的快照checkout成本地分支，仓库快照本身无法修改，只能变成本地分支才能改
```bash
git checkout -b branch_name tag_name
$git checkout -b branch1 2014.1.1
```

* 当前的文件夹中文件信息汇总
```bash
# 列出当前目录及子目录中所有文件
$git ls-files

# 统计当前目录及子目录中所有文件的行数
$git ls-files | xargs cat | wc -l
```

* 合并两分支代码，与rebase命令有些区别，体现在分支树节点上
```bash
git merge branch_a   
# 将社区分支合并到当前分支中
$git merge stable/icehouse 
```
* 合并分支区别 rebase／merge
```bash
master branch : patch1<-patch2<-patch3
b1 branch from patch2 : patch1<-patch2<-patch4
b2 branch from patch2 : patch1<-patch2<-patch4
# 检查分支合并结果
git log --oneline 
b1-git merge master : patch1<-patch2<-patch4<-patch3<-auto-merge-patch 'Merge branch 'master' into b1'
                                    把master中最新提交归并到b1分支最后，并产生一个自动合并提交
b2-git rebase master : patch1<-patch2<-patch3<-patch4 归并到master分支中，跟在最近提交后面
```

* 修改本地已提交的历史
> [Link1](https://git-scm.herokuapp.com/book/en/v2/Git-Tools-Rewriting-History)<br>
> [Link2](https://jacopretorius.net/2013/05/amend-multiple-commit-messages-with-git.html)
```bash
# Show the last 9 commits in a text editor, @ is shorthand for HEAD, 
# and ~ is the commit before the specified commit
# in a text editor change 'pick' to 'e' (edit), and save and close the file.
# Git will rewind to that commit
$git rebase -i @~9   

$git add -A
#make changes
$git commit --amend 
#discard the last commit, but not the changes to the files
$git reset @~  
#Git will replay the subsequent changes on top of your modified commit
$git rebase --continue  
```
* 回退变更,提交
```bash
# 二者最大区别在于,如果commit没有publish出去，那么就可以在本地丢弃，用reset即可
# 如果已经publish到公共repo了，那么就用revert，它会产生新的commit去undo已存在提交
git reset/revert                      
```
* 从HEAD回退到指定提交位置，未提交的任何本地改变都会丢弃
```bash
$git reset --hard 0d1d7fc32
```
* 或者回退到分支最新提交点
```bash
$git reset --hard HEAD
```
* 先临时保存未提交的本地变更，然后回退到提交点，再将临时保存修改应用到新基点。
```bash
$git stash; git reset --hard 0d1d7fc32; git stash pop
```
* 通过软复位也可以达到上一命令相同效果  hard/soft前后两种方式区别就在于是否保留未提交更改
```bash
$git reset --soft 0d1d7fc32
```
* 回退已提交的三个commit
```bash
# 自动产生一个revert的commit
$git revert a867b4af 25eee4ca 0766c053
```
* 回退从当前HEAD往前2个commit
```bash
$git revert HEAD~2..HEAD
```
* 回退提交，不自动产生revert的commit，手动提交
```bash
$git revert --no-commit 0766c053..HEAD; git commit
```
* 查看某个提交中哪些文件被修改
```bash
$git show e96a53a68b2ed1ce9b98661b07f8071e789d2319
```

* 在当前代码分支中合并某个提交代码
```bash
git cherry-pick -x
```
* 在当前代码分支中合并其他分支某个提交代码
```bash
git cherry-pick branch
# 获取dev分支最后一个提交
$git cherry-pick dev  

# 获取dev分支倒数第二提交
$git cherry-pick dev^  

# 获取dev分支倒数第三提交
$git cherry-pick dev~2  
```

* 比较本地分支和remote分支差异
```bash
# 比较本地master分支和remote master分支的差异
$git diff origin/master  

# 比较本地myBranch分支和remote master分支的差异
$git diff myBranch origin/master  
```
* 比较某个文件在不同tag快照中区别
```bash
git diff tag1 tag2  -- filename

# 查看文件两标签间修改
$git diff 0.12.0 0.13.0 -- glanceclient/common/http.py  
```
* 查看修改提交出现在哪些标签中
```bash
git tag --contains commitID
$git tag --contains dbb242b776908ca50ed8557ebfe7cfcd879366c8
```
* 查看某个代码分支所有提交日志summary
```bash
git log --oneline <branch>
# 查看当前分支日志列表
$git log --oneline  
```

* 查看分支中某个作者的提交列表
```bash
# 查看匹配name的作者所有提交详情
git log --author <name>  

# 查看匹配name的作者在指定文件目录下提交详情
git log --author <name> ./src-1/test/ ./src-2/test/

# 查看匹配name的作者所有提交简要统计
git log --author <name> --oneline --shortstat

# 查看匹配name的作者所有提交增删简要统计
git log --author <name> --oneline --numstat

# 查看匹配name的作者所有提交的统计
git shortlog --author <name>
git shortlog -s | grep <name>
git log --author <name> | grep <name> | wc -l 

# 查看匹配作者的全部增删代码行统计
git log --author=<name> --pretty=tformat: --numstat | awk \
'{ add += $1; subs += $2; loc += $1 - $2 } END \
{ printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'
```

* 查看代码分支在两个tag快照之间的所有提交日志
```bash
git log tag1..tag2
```
* 查看某个文件的所有修改细节
```bash
git blame filename
# 查看http.py文件每一行修改记录
$git blame glanceclient/common/http.py  
```
* 创建源代码文件补丁
```bash
# 在当前分支下为最新的一个提交(head -1)打出补丁
$git format-patch -1 HEAD  

# 在当前分支下为某一个提交(sha -1)打出补丁
$git format-patch -1   
```

* GUI方式查看分支修改记录
```bash
gitk
gitk file
```

---

### 代码的review
1. 安装支持git的reviewt专用软件 (http://en.wikipedia.org/wiki/List_of_tools_for_code_review)

2. 通过gerrit review的命令来操作, 或者通过git-review命令

	- git-review是openstack组织开发贡献的. 需要额外安装
	```bash
	sudo pip install git-review
	```
	或
	```bash
	aptitude install git-review
	```
	- 创建review配置文件, 需要在本地git repo的根目录, 即.git同一级目录, 创建一个.gitreview的配置文件
	```bash
	touch .gitreview
	```
	- .gitreview配置文件类似Windows .ini文件格式，存放Gerrit安装的相关信息
	模板如下:
	```bash
	[gerrit]
	scheme=ssh   
	host=gitreview.example.com
	port=29418
	project=XXX.git
	defaultbranch=master
	```
	- 设定默认rebase到zero ，将使git-review不会rebase changes (same as the -R command line option)
	```bash
	[gerrit]
	scheme=ssh   --default scheme (ssh/http/https) of gerrit remote
	host=review.example.com  --gerrit remote server
	port=29418
	project=department/project.git   --对应的文件仓库
	defaultbranch=master
	defaultremote=review
	defaultrebase=0
	track=0
	```   
   - 创建全局review用户名
	```bash
	git config -l    -- list all current config
	# update author/email per repo, useful for current repo config
	git config user.name "Gene"
	git config user.email "Gene@genesis.org"
	
	# update global author/email， impact on all git repo
	git config --global user.name "Gene"
	git config --global user.email "Gene@genesis.org"
	
	#remove global config
	git config --global --unset-all user.name
	```
	*当相同配置通过.gitreview配置文件或命令参数同时生效时候，命令行参数优先*

    - 设定commit-msg中自动产生Change-Id (https://git.eclipse.org/r/Documentation/cmd-hook-commit-msg.html)
	```bash
	$ scp -p -P 29418 king@review.example.com:hooks/commit-msg ~/source/.git/hooks/
	```
	或
	```bash
	$ curl -Lo ~/source/.git/hooks/commit-msg https://review.example.com/tools/hooks/commit-msg
    ```	
	*如果出现权限问题，一般是gerrit服务器上没有保存本机公钥，需要通过ssh-keygen命令产生~/.ssh/id_rsa.pub，并复制到gerrit管理界面上。
	如之后得到的commit-msg文件没有执行权限，还需要修改一下执行权限*
	```bash
	$ chmod u+x ~/source/.git/hooks/commit-msg
	```
	Ok，修改commit到本地之后就会在提交信息中产生一个Change-ID了, 如Change-Id: I5b8ecff9d0b6dddda4c76e162629017ac5026341

    - git review 操作参考
	在本地repo提交commit后，即可通过 git review创建review request, 或者 git review branchName 创建指定分支的review request
	<br>如下结果输出:<br>
		
		```bash
		$ git review master
		remote: Resolving deltas: 100% (4/4)
		remote: Counting objects: 61302, done
		remote: Processing changes: new: 1, refs: 1, done
		remote:
		remote: New Changes:
		remote:   https://gitreview.example.com:8443/20509
		remote:
		To ssh://Gene@gitreview.example.com:29418/xxx.git
		* [new branch]      HEAD -> refs/for/master/master

		$ To fetch a remote change number 3004:

			   $ git-review -d 3004
			   Downloading refs/changes/04/3004/1 from gerrit into
			   review/someone/topic_name
			   Switched to branch 'review/someone/topic_name
			   $ git branch
				 master
			   * review/author/topic_name

		 Gerrit looks up both name of the author and the topic name from Gerrit to name a local branch.
		 This facilitates easier identification of changes.

		 $ To fetch a remote patchset number 5 from change number 3004:

			   $ git-review -d 3004,5
			   Downloading refs/changes/04/3004/5 from gerrit into
			   review/someone/topic_name-patch5
			   Switched to branch 'review/someone/topic_name-patch5
			   $ git branch
				 master
			   * review/author/topic_name-patch5	 

		 $ To send a change for review and delete local branch afterwards:

			   $ git-review -f
			   remote: Resolving deltas:   0% (0/8)
			   To ssh://username@review.example.com/department/project.git
				* [new branch]      HEAD -> refs/for/master/topic_name
			   Switched to branch 'master'
			   Deleted branch 'review/someone/topic_name'
			   $ git branch
			   * master
		```
	- 命令行参数具体说明参考 man git-review
		```bash
		CONFIGURATION
			 This utility can be configured by adding entries to Git configuration.

			 The following configuration keys are supported:

			 gitreview.username
						   Default username used to access the repository. If not specified 
						   in the Git configuration, Git remote or .gitreview file, the user 
						   will be prompted to specify the username.

						   Example entry in the .gitconfig file:

								 [gitreview]
								 username=mygerrituser

			 gitreview.scheme
						   This setting determines the 

			 gitreview.host
						   This setting determines the default hostname of gerrit remote

			 gitreview.port
						   This setting determines the default port of gerrit remote
						   
			gitreview.project
						   This setting determines the default name of gerrit git repo

			 gitreview.remote
						   This setting determines the default name to use for gerrit remote

			 gitreview.branch
						   This setting determines the default branch

			 ...
		```								


		   
