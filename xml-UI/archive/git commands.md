### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is git commands used usually*

---

- [git add](#git-add)
- [git branch](#git-branch)
- [git blame](#git-blame)
- [git config](#git-config)
- [git clone](#git-clone)
- [git commit](#git-commit)
- [git checkout](#git-checkout)
- [git cherry-pick](#git-cherry-pick)
- [git diff](#git-diff)
- [git format-patch](#git-format-patch)
- [git apply-patch](#git-apply-patch)
- [git grep](#git-grep)
- [git gc](#git-gc)
- [git init](#git-init)
- [gitk](#gitk)
- [git log](#git-log)
- [git ls-files](#git-ls-files)
- [git merge/rebase](#git-merge-rebase)
- [git pull](#git-pull)
- [git push](#git-push)
- [git rm/clean](#git-rm)
- [git remote](#git-remote)
- [git reset/revert](#git-reset-revert)
- [git review](#git-review)
- [git rev-list](#git-rev-list)
- [git status](#git-status)
- [git show](#git-show)
- [git tag](#git-tag)
- [术语表](https://mp.weixin.qq.com/s/5EvrlCVc7g0LX7DMhI-ZnQ)
- [使用子模块和子树来管理Git项目](https://mp.weixin.qq.com/s/RIM0y2Stwf8HiSbw7o8kcQ)

### git config
```console
# list 代码仓库全部属性
git config -l

# 设置当前git用户名及邮件属性，仅作用于当前git仓库
git config user.name "Gene"
git config user.email "Gene@genesis.org"

# 设置proxy
git config http.proxy myproxy.server:8888
git config https.proxy myproxy.server:8888

# 设置全局性git用户名及邮件属性，会作用于全部git仓库
git config --global user.name "Gene"
git config --global user.email "Gene@genesis.org"

# remove global config
git config --global --unset-all user.name

# 如果仓库密码发生改变导致push等操作认证失败，需要重置一下当前密码
git config --unset user.password

# mac系统默认配置 credential.helper=osxkeychain 
# 所有本地仓库都会使用同一个用户名和密码，每次git push不用输入密码
# 想使用不同账户 git push，需要删除系统默认的印信认证方式
# 否则 remote: Permission to user1/incubator-griffin.git denied to user2
git config --local --unset credential.helper
git config --global --unset credential.helper
git config --system --unset credential.helper
```

### git init
```console
# 初始化一个空的本地git仓库
git init

# 建立一个可供其他机器下载的remote repo
# 先在remote机器上指定目录中init一个仓库
# 本地创建一个远程git repo映射的alias myAppName
git remote add myAppName ssh://192.168.149.128/~/source/app/.git 

# 将本地myAppName目录下master分支推送到remote repo
git push myAppName master
```

### git clone 
```console
# 克隆一个远端代码仓库到本地
$git clone https://github.com/openstack/glance.git
```

### git remote
```console
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

# 更新本地的remote代码源,不会覆盖本地分支
git remote update

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

### git pull
```console
# 获取远端代码仓库中最新的代码，并且覆盖本地分支
git pull
```

### git push
```console
# 把本地当前最新分支push到Eugene目录下remote的bugs分支
git push Eugene HEAD:bugs

# 把本地当前最新分支(dev)push到Eugene目录下remote的同名分支dev
git push Eugene dev

# 把本地glance目录下最新分支push到remote的分支(refs/heads/icehouse)
git push glance HEAD:refs/heads/icehouse

# Eugene目录中master分支不存在,推送失败
git push Eugene master:refactor/testcases   
error: src refspec master does not match any.
error: failed to push some refs to 'https://github.com/toyboxman/incubator-griffin.git'
# 推送Eugene目录下m0分支到remote的refactor/testcases分支成功
git push Eugene m0:refactor/testcases
To https://github.com/toyboxman/incubator-griffin.git
 * [new branch]      m0 -> refactor/testcases

# Eugene目录中pr-345分支与remote的headOption分支有差异,推送失败
git push Eugene pr-345:headOption
To https://github.com/toyboxman/incubator-griffin.git
 ! [rejected]        pr-345 -> headOption (non-fast-forward)
error: failed to push some refs to 'https://github.com/toyboxman/incubator-griffin.git'
hint: Updates were rejected because a pushed branch tip is behind its remote
hint: counterpart. Check out this branch and integrate the remote changes
hint: (e.g. 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
# 强制推送成功 --force
git push -f Eugene pr-345:headOption
remote: Resolving deltas: 100% (9/9), completed with 9 local objects.
To https://github.com/toyboxman/incubator-griffin.git
 + cd523f4...62ef1ad pr-345 -> headOption (forced update)
```

### git status
```console
# 查看仓库中文件状态
git status

# 查看仓库中untracked files
git status -u

# 忽略仓库中untracked files
# equivalent to git status --untracked-files=no
git status -uno

# 如果有些modified文件不想放入提交列表，可以放入.gitignore，也可以临时用命令处理
# 设置需要临时忽略的文件pom.xml
git update-index --assume-unchanged pom.xml
# 设置临时忽略的文件pom.xml改为正常状态
git update-index --no-assume-unchanged pom.xml
```

### git add
```console
# 将source中所有文件子目录都加入待提交staged状态
git add ./source 

# 文字菜单方式查看当前所有待提交文件状态
git add -i  

# 把所有修改文件加入待提交staged状态
git add --all  
```

### git rm
git rm 用来删除指定文件
```console
# untracked:新建文件  unstaged:修改过repo tree上的文件 staged:git add后待commit的文件
# 将source中所有文件子目录都从待提交staged状态改成untracked状态
# 此命令仅对git add untracked-files起到undo的效果，如果对git add unstaged-files执行会从本地tree中删除文件
git rm --cached -r ./source

# 将git add unstaged-files文件从staged状态undo回原状态unstaged
git reset @ readme.md

# 将文件从tracked的状态置为deleted，并且从文件系统中删除
git rm readme.md
# 置为deleted状态的文件只能通过重新checkout来恢复
git checkout HEAD readme.md
git checkout @ readme.md
```
如果需要删除文件太多了, 例如 git reset 如果不强制--hard则会保留回滚产生的大量文件，一个个rm太麻烦可以使用 git clean
```console
# 使用clean前使用 -n or --dry-run 选项来预览删除结果列表，防止错误删除文件
# 清除 untracked files
git clean -f
# 清除新产生的directories和files
git clean -f -d
# 清除 ignored files
git clean -f -X
# 清除 ignored和non-ignored files
git clean -f -x
```

### git commit
```console
# 提交全部更改的文件
git commit -a 

# 修正本地分支的代码提交
git commit --amend 

# 修正本地分支的代码提交中作者信息
git commit --amend --author="Gene <Gene@genesis.org>" --no-edit
```

### git branch
```console
# 查看当前代码库全部分支
git branch -a  

# 查看本地分支和远程分支关系,包括分支间关联,提交状态对比
$ git branch -vv
scala         a366fd6 add scala static analysis plugin
scala-lint    a366fd6 [origin/master: ahead 1, behind 12] add scala static analysis plugin
# 如果本地分支没有track remote branch， 如scala，可以用任意一个下面命令设定upstream来track状态
$ git branch -u origin/master scala
$ git branch --set-upstream-to=origin/master scala
scala         a366fd6 [origin/master: ahead 1, behind 12] add scala static analysis plugin
scala-lint    a366fd6 [origin/master: ahead 1, behind 12] add scala static analysis plugin
# 取消upstream设定
git branch --unset-upstream scala

# 强制删除master分支
git branch -D master  

# 删除master分支，但需要所有本地提交已经合并到upstream或者没有变化
git branch -d master  

# 重命名当前分支成 master-dev
git branch -m master-dev
# 重命名 master-dev 为 master
git branch -m master-dev master

# 查看哪些本地分支包含某个提交
git branch --contains e96a53a68b2ed1ce9b98661b07f8071e789d2319
# 查看哪些remote分支包含某个提交
git branch -r --contains e96a53a68b2ed1ce9b98661b07f8071e789d2319
# 查看哪些本地分支不包含某个提交
git branch --no-contains 45eb3ae0a5ccce683a74625409bd015c1fd6d312
```

### git checkout
```console
# 将glance/stable/icehouse分支checkout成一个本地master分支
git checkout -b master glance/stable/icehouse

# 从当前分支切换到master分支
git checkout master

# 把tag(2014.1.1)对应的快照checkout成本地分支tag-dev
# 仓库的快照本身不能修改,只能变成本地分支才能修改
git checkout -b tag-dev 2014.1.1

# 临时切换到某个commit位置,切换后会与当前的分支脱离,处于无分支checkout状态
git checkout 0d1d7fc32

# 将当前分支代码回滚到upstream HEAD位置
git checkout -f  

# 文件处于untracked, 丢弃在当前分支中对Run.java文件的修改
git checkout -- Run.java  

# 从dev分支中获取Run.java，覆盖本地分支文件
git checkout dev -- Run.java  
# 从某个commit中获取Run.java，覆盖本地分支文件
git checkout 0d1d7fc32 -- Run.java 

# 从dev分支中获取./java/src目录下所有文件，新建或覆盖本地分支./java/src/下文件
git checkout dev -- ./java/src/
```

### git tag 
```console
# 查看当前代码库全部tag快照
git tag

# 查看如何创建删除标签
git tag --help  

# 查看commit出现在哪些仓库标签快照中
$git tag --contains dbb242b776908ca50ed8557ebfe7cfcd879366c8
```

### git ls-files
```console
# 列出当前目录及子目录中所有文件
git ls-files

# 查找Example.java 是否存在当前branch中
git ls-files | grep Example.java

# 统计当前目录及子目录中所有文件的行数
git ls-files | xargs cat | wc -l
```

### git merge rebase
+ [Rewriting History](https://git-scm.herokuapp.com/book/en/v2/Git-Tools-Rewriting-History)<br>
+ rebase修改提交历史[[1](https://mp.weixin.qq.com/s/OW7ZpE2wvhwVOhLBVODHdA), [2](https://mp.weixin.qq.com/s/xLPDiPRzNnLLTrTlWJqWCA)]
+ [rebase一次修改多个提交记录](https://jacopretorius.net/2013/05/amend-multiple-commit-messages-with-git.html)

```console
# 将社区分支(stable/icehouse)合并到当前分支中
git merge stable/icehouse 

# b1 b2分支上做rebase/merge 操作结果的差异
master branch : patch1 <- patch2 <- patch3
b1 branch forking from patch2 : patch1 <- patch2 <- patch4
b2 branch forking from patch2 : patch1 <- patch2 <- patch4
# 检查分支合并结果, merge后会产生合并点, rebase后原分支顺序不会保留
git log --oneline 
b1 > git merge master : patch1<-patch2<-patch4<-patch3<-auto-merge-patch 'Merge branch 'master' into b1'
                                    把master上patch2之后提交都合并到b1分支最后，并产生一个自动合并提交
b2 > git rebase master : patch1<-patch2<-patch3<-patch4
                                    把master上patch2之后提交都插入到b1分支patch2之后

##################################									
# 修改本地提交历史基本步骤(如果本地分支已经push到remote, 操作修改会产生本地与remote repo的冲突)
##################################
# rebase当前分支至倒数9个提交，在默认text editor显示修改提交列表
git rebase -i @~9   
# 在vi编辑器中把想修改的commit状态从'pick'改成'e', 保存退出
# 如果想调整提交顺序, 在vi编辑器中调整这些commits list的前后顺序即可
# 修改需要调整的文件，然后用add命令置为staged状态
git add -A
# 提交修改
git commit --amend 
# replay调整后的提交list
git rebase --continue  

##################################									
# 拆分提交基本步骤
##################################
# 步骤跟修改一致，唯一区别就是多做一个软回退reset操作
git rebase -i @~9 
# 修改commit状态 pick -> edit，保存退出
# 回退当前commit中所有文件修改至前一个提交时的状态
git reset @~1
# 然后重新选择并组织文件提交
git add; git commit
git rebase --continue 
# 上述的这种拆分会改变旧的commit的hash ID，如果想保持旧的commit hash ID，可以只拆分部分文件
# 将当前commit中新增文件 file_created.java 回退到前一个commit的状态
git reset @~1 -- file_created.java
# 回退操作产生一个delete新增文件的状态
git status
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	deleted:    file_created.java
# 修改当前commit，去除掉新增文件
git commit --amend
git status
Untracked files:
  (use "git add <file>..." to include in what will be committed)
	file_created.java	
# 将 file_created.java 提交，这样旧commit可以保留，拆分文件提交到新commit
git add file_created.java
git commit

##################################									
# 合并多个提交基本步骤
##################################
# rebase本地提交
git rebase -i @~3
pick f7f3f6d Change my name a bit
pick 310154e Update README formatting and add blame
pick a5f4a0d Add cat-file
# squash是把后两合并进前一个，保留commit log message， 允许自己修改
pick f7f3f6d Change my name a bit
squash 310154e Update README formatting and add blame
squash a5f4a0d Add cat-file
# 新commit log message由三个提交信息合并如下
Change my name a bit

Update README formatting and add blame

Add cat-file

# replay
git rebase --continue  
# fixup和squash一样功能，区别仅是不保留合并进去的commit log message
pick f7f3f6d Change my name a bit
fixup 310154e Update README formatting and add blame
fixup a5f4a0d Add cat-file
# log message仍旧是第一个
Change my name a bit
```

### git reset revert
```console
# reset和revert区别在于, 如果commit没有push到repo, 那么用reset即可在本地丢弃
# 如果已经push到remote的仓库, 那么就需要用revert, 产生一个新的commit去undo已存在提交

# 从HEAD回退到指定提交位置, 未push的任何本地commit都会丢弃
git reset --hard 0d1d7fc32

# 回退到分支HEAD提交点,未保存的修改也会丢弃
git reset --hard HEAD

# 先临时保存未提交的本地变更, 然后回退到提交点, 再将临时保存修改应用到新基点
git stash; git reset --hard 0d1d7fc32; git stash pop

# 临时保存指定文件
git stash -- src/main/build.xml

# git stash是一组操作命令，用来临时保存更新修改。通过tab键可以自动提示
git stash list
git stash push <file>
git stash clear
git stash show

# hard/soft两种方式区别就在于回退操作后是否保留所有回退commit中修改的文件
# --hard会清除回退的所有修改文件，--soft会将修改文件置为untracked 默认不指定hard就都是soft模式
# 如果想把本地一个提交中某些文件删除，可以先soft reset，然后再重新选择提交
git reset --soft 0d1d7fc32

# 将指定文件回退到前两个提交的点
git reset @~2 -- maven_install.json
# 所有文件回退到HEAD提交点
git reset @

# 回退已提交的三个commit, git自动产生一个revert的commit
git revert a867b4af  25eee4ca 0766c053

# 回退从当前HEAD往前2个commit, git自动产生一个revert的commit
git revert HEAD~2..HEAD

# 回退提交, 不自动产生revert的commit, 手动提交
git revert --no-commit 0766c053..HEAD; git commit
```

### git cherry-pick
```console
# 在当前分支中获得dev分支commit ID是6d17945cefa的提交
git cherry-pick dev 6d17945cefa

# 在当前分支中获取dev分支最后一个提交
git cherry-pick dev  

# 在当前分支中获取dev分支倒数第二提交
git cherry-pick dev^  

# 在当前分支中获取dev分支倒数第三提交
git cherry-pick dev~2  

# 在当前分支中获取dev分支三个提交
git cherry-pick dev~2 dev^ dev
# 或者通过range设定3个commits，
# range规则是(a..b], a比b旧，a不取，取a的下一个到b所有commits 
git cherry-pick dev~3..dev
```

### git diff
```console
# 比较当前master分支和remote的master分支的差异
git diff origin/master  
# 比较当前master分支和remote的master分支中指定文件的差异
git diff origin/master  -- Run.java

# 比较本地myBranch分支和remote master分支的差异
git diff myBranch origin/master  

# 比较本地myBranch分支和remote master分支的某个目录差异
git diff myBranch origin/master -- src/main/java 

# 查看文件两标签快照(0.12.0-0.13.0)之间的差异
git diff 0.12.0 0.13.0 -- glanceclient/common/http.py  

# 比较当前分支指定文件最新提交和前一个提交间的差异
git diff @:./java_deps.bzl @^:./java_deps.bzl
```

### git log 
```console
# 简要汇总flash-dev分支所有提交日志
git log --oneline flash-dev

# 简要汇总当前分支src目录下前5条日志
git log -5 --oneline  -- src/

# 查看已删除文件路径src/main/java所对应的commit记录
git log --oneline -- src/main/java 或者 git rev-list -n 1 HEAD -- src/main/java
git log f2edbe81730 --summary 

# 汇总commit提交文件信息
git log --stat f2edbe81730

# 查看src/main/java目录下匹配rpc关键字的所有提交
git log --grep=rpc -- src/main/java

# 查找所有分支历史记录中是否匹配指定文件，相当于find命令，但find无法搜索非当前代码分支
# -- **／PolicyFacadeImplAjIf* 指定当前目录所有嵌套子目录中查询文件名
# 最好在git根目录下执行，可以find全部工程目录
git log --all --summary -- "**/PolicyFacadeImplAjIf*"

# 查找所有被Batman删除的文件的提交记录
git log --diff-filter=D --author=Batman --summary  

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

# 查看时间段(2018全年)所有提交的概要
git shortlog --since=2018 --before=2019

# 查看匹配作者的全部增删代码行统计
git log --author=<name> --pretty=tformat: --numstat | awk \
'{ add += $1; subs += $2; loc += $1 - $2 } END \
{ printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'

# 汇总当前分支在两个tag快照之间的所有提交日志
git log tag1..tag2
# 汇总两分支之间的提交差异
git log master..origin/master
```

### git rev-list
```console
# 统计指定提交与当前branch的HEAD之间提交总数
git rev-list a9e5e1dc6afd..@ --count
# 统计指定提交与指定branch的HEAD之间提交总数
git rev-list 178ac735e4097f..origin/master --count

# 统计两个分支提交数目差
git branch -vv
  im-trace  4ed5411 [origin/xbranch: ahead 7, behind 1059] 
* uatrace  1fdddc9 [origin/xbranch: behind 61]

# 查看uatrace&im-trace相对xbranch之间behind提交数目差值 1059-61=998
git rev-list @ ^im-trace --count
998
# 查看uatrace&im-trace相对xbranch之间ahead提交数目差值 7-0=7
git rev-list @..im-trace --count
7

# 比较两个提交在分支上的先后次序，数字大说明提交在前，也就是1fdddc9为bf5126祖辈
git rev-list bf5126..@ --count
29
git rev-list 1fdddc9..@ --count
62
# 从子辈往祖辈统计，无结果输出
git rev-list bf5126..1fdddc9 --count
# 从祖辈往子辈统计，62-29=33
git rev-list 1fdddc9..bf5126 --count
33
# 不统计输出就列出差距中所有提交hash
git rev-list 1fdddc9..bf5126
bf5126b58f5b49933efeadee3fbb6689e91bec2b
...
e786c78182a1c51cb7bcf75ff71fd9d6b69b1f09

# 统计当前branch全部提交总数
git rev-list --count HEAD
# 统计时间段内当前branch全部提交的总数
git rev-list --count @ --since="Dec 3 2015"  --before="Jan 3 2016"
# 统计时间段内所有branch全部提交的总数
git rev-list --count --since="Dec 3 2015"  --before="Jan 3 2016" --all
# 查看时间段内所有branch全部提交的总数,但忽略merge-commits数目
git rev-list --count --since="Dec 3 2015"  --before="Jan 3 2016" --all --no-merges
```

### git blame
```console
# 查看http.py文件每一行修改记录
git blame glanceclient/common/http.py  
```

### git show
```console
# 显示master分支最后一个提交的内容
git show master @
# 显示master分支倒数第二个提交的内容
git show master^ 
# 显示master分支倒数第三个提交的内容
git show master~2
# 显示master分支倒数第四个提交的内容
git show master^~2

# 查看某个提交中哪些文件被修改
git show e96a53a68b2ed1ce
# 查看某个提交中test.java内容
git show e96a53a68b2ed1ce:src/test.java

# 查看dev分支中test.java内容
git show dev:test/src/test.java

# 查看dev分支中src目录下内容
# 支持tab提示功能
git show dev:test/src/
# ls-tree提供相似功能
git ls-tree --full-tree -r --name-only dev | less
# ls-files提供当前工作branch上文件列表
git ls-files

# 把dev分支中test.java导入当前分支中
mkdir -p test/src; git show dev:test/src/test.java > ~/test/src/test.java
```

### git format-patch
```console
# 在当前分支下,为最新的一个提交(head -1)打出补丁
git format-patch -1 HEAD  

# 给dev分支最新的一个提交(head -1)打出补丁
git format-patch -1 dev  

# 给当前分支从f5f8ff546b3开始到HEAD之间所有提交打出补丁
git format-patch f5f8ff546b3  
# 给当前分支从f5f8ff546b3开始到HEAD-2之间所有提交打出补丁
git format-patch f5f8ff546b3..@~2

# 给当前分支中指定 f5f8ff546b3提交打出补丁
git format-patch -1 f5f8ff546b3  

# 把当前分支最新两个提交打成一个补丁
# 默认前面几个命令会按一个提交一个补丁方式产生00001/2/3...-xxx.patch
# --stdout会将多个补丁合并起来，但不改变提交顺序，git am my.patch之后产生多个提交记录
git format-patch -2 --stdout > my.patch
```

### git apply-patch
```console
# 查看补丁的内容
git apply --stat my.patch
# 检查patch是否存在错误
git apply --check my.patch
# 应用patch产生unstaged changes
git apply my.patch

#应用patch产生一个commit
git am my.patch
```

### git grep
```console
# 搜索当前branch当前目录中匹配的内容
# 由于repo本身有索引，所以速度远快于grep
# repo本身是树状，没有-r参数，默认全路径搜索
git grep -in 'if('

# 搜索多个branch
git grep -in 'Converter' origin/master origin/dev
# 如果当前所在目录是project/src/main/java则上面命令执行目录等同于下面命令
git grep -in 'Converter' origin/master:src/main/java origin/dev:src/main/java

# 相当于grep中 --include参数
git grep -in 'if(' -- '*.java' '*.cpp'
# 在repo指定路径下指定文件类型搜索关键字
git grep "ProfileProxy" -- policy/staging/*.java
# 在policy目录下搜寻所有java文件，exclude 路径policy/api_attic下的java文件
git grep -in "ProfileProxy" -- ':^policy/api_attic' -- policy/*.java
# 当前路径src目录下按照正则表达式搜索包含sync并带括号的匹配项
# 由于括号是正则表达式保留符号因此用反斜杠转义
git grep -inE "public static .*sync.*\(" src/
```

### git gc
```console
# 长期运行后 .git目录会占用大量空间,可以通过gc来回收空间
git gc --aggressive --prune
```

### gitk 
```console
# GUI方式查看分支修改记录
gitk

# 查看文件修改记录
gitk file
```

---

### [git review](https://osm.etsi.org/wikipub/index.php/Using_git-review_to_push_and_review_changes)
1. 安装支持 review [专用软件](http://en.wikipedia.org/wiki/List_of_tools_for_code_review)

2. 通过gerrit review的命令来操作, 或者通过git-review命令

	- git-review是openstack组织开发贡献的. 需要额外安装
	```console
	sudo pip install git-review
	```
	Ubuntu平台
	```console
	aptitude install git-review
	```
	Mac平台
	```console
	brew install git-review
	```
	- 创建review配置文件, 需要在本地git repo的根目录, 即.git同一级目录, 创建一个.gitreview的配置文件
	```console
	touch .gitreview
	```
	- .gitreview配置文件类似Windows .ini文件格式，存放Gerrit安装的相关信息
	模板如下:
	```console
	[gerrit]
	scheme=ssh   
	host=gitreview.example.com
	port=29418
	project=XXX.git
	defaultbranch=master
	```
	然后执行`git review XXX-branch`,根据上面设定安装和配置当前repo的第一个review request。成功操作后，.gitreview就不起作用了，可以删除。
	```console
	# 如果遇到如下区域设定错误，可以通过后面命令来安装重置当前locale
	locale.Error: unsupported locale setting
	
	# 如果希望默认设定locale， 可以把两条export命令放到 .bashrc
	export LC_ALL="en_US.UTF-8"
	export LC_CTYPE="en_US.UTF-8"
	sudo dpkg-reconfigure locales
	```
	- 设定默认rebase到zero ，将使git-review不会rebase changes (same as the -R command line option)
	```console
	[gerrit]
	scheme=ssh   --default scheme (ssh/http/https) of gerrit remote
	host=review.example.com  --gerrit remote server 可以查看.git/config中配置
	port=29418
	project=department/project.git   --对应的文件仓库
	defaultbranch=master
	defaultremote=review
	defaultrebase=0
	track=0
	```   
   - 创建全局review用户名
	```console
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
	```console
	$ scp -p -P 29418 king@review.example.com:hooks/commit-msg ~/source/.git/hooks/
	```
	或
	```console
	$ curl -Lo ~/source/.git/hooks/commit-msg https://review.example.com/tools/hooks/commit-msg
    ```	
	*如果出现权限问题，一般是gerrit服务器上没有保存本机公钥，需要通过ssh-keygen命令产生~/.ssh/id_rsa.pub，并复制到gerrit管理界面上。
	如之后得到的commit-msg文件没有执行权限，还需要修改一下执行权限*
	```console
	$ chmod u+x ~/source/.git/hooks/commit-msg
	```
	Ok，修改commit到本地之后就会在提交信息中产生一个Change-ID了, 如Change-Id: I5b8ecff9d0b6dddda4c76e162629017ac5026341

    - git review 操作参考
	在本地repo提交commit后，即可通过 git review创建review request, 或者 git review branchName 创建指定分支的review request
	<br>如下结果输出:<br>
		
		```console
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
		```console
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


		   
