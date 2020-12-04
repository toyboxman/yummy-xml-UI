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
- [�����](https://mp.weixin.qq.com/s/5EvrlCVc7g0LX7DMhI-ZnQ)
- [ʹ����ģ�������������Git��Ŀ](https://mp.weixin.qq.com/s/RIM0y2Stwf8HiSbw7o8kcQ)

### git config
```console
# list ����ֿ�ȫ������
git config -l

# ���õ�ǰgit�û������ʼ����ԣ��������ڵ�ǰgit�ֿ�
git config user.name "Gene"
git config user.email "Gene@genesis.org"

# ����proxy
git config http.proxy myproxy.server:8888
git config https.proxy myproxy.server:8888

# ����ȫ����git�û������ʼ����ԣ���������ȫ��git�ֿ�
git config --global user.name "Gene"
git config --global user.email "Gene@genesis.org"

# remove global config
git config --global --unset-all user.name

# ����ֿ����뷢���ı䵼��push�Ȳ�����֤ʧ�ܣ���Ҫ����һ�µ�ǰ����
git config --unset user.password

# macϵͳĬ������ credential.helper=osxkeychain 
# ���б��زֿⶼ��ʹ��ͬһ���û��������룬ÿ��git push������������
# ��ʹ�ò�ͬ�˻� git push����Ҫɾ��ϵͳĬ�ϵ�ӡ����֤��ʽ
# ���� remote: Permission to user1/incubator-griffin.git denied to user2
git config --local --unset credential.helper
git config --global --unset credential.helper
git config --system --unset credential.helper
```

### git init
```console
# ��ʼ��һ���յı���git�ֿ�
git init

# ����һ���ɹ������������ص�remote repo
# ����remote������ָ��Ŀ¼��initһ���ֿ�
# ���ش���һ��Զ��git repoӳ���alias myAppName
git remote add myAppName ssh://192.168.149.128/~/source/app/.git 

# ������myAppNameĿ¼��master��֧���͵�remote repo
git push myAppName master
```

### git clone 
```console
# ��¡һ��Զ�˴���ֿ⵽����
$git clone https://github.com/openstack/glance.git
```

### git remote
```console
# �ڱ��ش���һ��aliasΪglance��remote�ֿ�(glance.git)ӳ��
git remote add glance https://github.com/openstack/glance.git

# �ڱ��ش���һ��aliasΪEugene��remote�ֿ�ӳ��
git remote add Eugene https://github.com/toyboxman/incubator-griffin.git

# list��ǰ�ֿ�����remote����Դ
git remote -v
Eugene  https://github.com/toyboxman/incubator-griffin.git (fetch)
Eugene  https://github.com/toyboxman/incubator-griffin.git (push)
glance  https://github.com/openstack/glance.git (fetch)
glance  https://github.com/openstack/glance.git (push)
origin  https://github.com/apache/incubator-griffin.git (fetch)
origin  https://github.com/apache/incubator-griffin.git (push)

# ɾ��remote����Դӳ��
git remote remove glance

# ���±��ص�remote����Դ,���Ḳ�Ǳ��ط�֧
git remote update

# �鿴Զ��repo
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

# ���remote��ɾ�����ػ����ڵ�stale��֧
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
# ��ȡԶ�˴���ֿ������µĴ��룬���Ҹ��Ǳ��ط�֧
git pull
```

### git push
```console
# �ѱ��ص�ǰ���·�֧push��EugeneĿ¼��remote��bugs��֧
git push Eugene HEAD:bugs

# �ѱ��ص�ǰ���·�֧(dev)push��EugeneĿ¼��remote��ͬ����֧dev
git push Eugene dev

# �ѱ���glanceĿ¼�����·�֧push��remote�ķ�֧(refs/heads/icehouse)
git push glance HEAD:refs/heads/icehouse

# EugeneĿ¼��master��֧������,����ʧ��
git push Eugene master:refactor/testcases   
error: src refspec master does not match any.
error: failed to push some refs to 'https://github.com/toyboxman/incubator-griffin.git'
# ����EugeneĿ¼��m0��֧��remote��refactor/testcases��֧�ɹ�
git push Eugene m0:refactor/testcases
To https://github.com/toyboxman/incubator-griffin.git
 * [new branch]      m0 -> refactor/testcases

# EugeneĿ¼��pr-345��֧��remote��headOption��֧�в���,����ʧ��
git push Eugene pr-345:headOption
To https://github.com/toyboxman/incubator-griffin.git
 ! [rejected]        pr-345 -> headOption (non-fast-forward)
error: failed to push some refs to 'https://github.com/toyboxman/incubator-griffin.git'
hint: Updates were rejected because a pushed branch tip is behind its remote
hint: counterpart. Check out this branch and integrate the remote changes
hint: (e.g. 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
# ǿ�����ͳɹ� --force
git push -f Eugene pr-345:headOption
remote: Resolving deltas: 100% (9/9), completed with 9 local objects.
To https://github.com/toyboxman/incubator-griffin.git
 + cd523f4...62ef1ad pr-345 -> headOption (forced update)
```

### git status
```console
# �鿴�ֿ����ļ�״̬
git status

# �鿴�ֿ���untracked files
git status -u

# ���Բֿ���untracked files
# equivalent to git status --untracked-files=no
git status -uno
```

### git add
```console
# ��source�������ļ���Ŀ¼��������ύstaged״̬
git add ./source 

# ���ֲ˵���ʽ�鿴��ǰ���д��ύ�ļ�״̬
git add -i  

# �������޸��ļ�������ύstaged״̬
git add --all  
```

### git rm
git rm ����ɾ��ָ���ļ�
```console
# untracked:�½��ļ�  unstaged:�޸Ĺ�repo tree�ϵ��ļ� staged:git add���commit���ļ�
# ��source�������ļ���Ŀ¼���Ӵ��ύstaged״̬�ĳ�untracked״̬
# ���������git add untracked-files��undo��Ч���������git add unstaged-filesִ�л�ӱ���tree��ɾ���ļ�
git rm --cached -r ./source

# ��git add unstaged-files�ļ���staged״̬undo��ԭ״̬unstaged
git reset @ readme.md

# ���ļ���tracked��״̬��Ϊdeleted�����Ҵ��ļ�ϵͳ��ɾ��
git rm readme.md
# ��Ϊdeleted״̬���ļ�ֻ��ͨ������checkout���ָ�
git checkout HEAD readme.md
git checkout @ readme.md
```
�����Ҫɾ���ļ�̫����, ���� git reset �����ǿ��--hard��ᱣ���ع������Ĵ����ļ���һ����rm̫�鷳����ʹ�� git clean
```console
# ʹ��cleanǰʹ�� -n or --dry-run ѡ����Ԥ��ɾ������б���ֹ����ɾ���ļ�
# ��� untracked files
git clean -f
# ����²�����directories��files
git clean -f -d
# ��� ignored files
git clean -f -X
# ��� ignored��non-ignored files
git clean -f -x
```

### git commit
```console
# �ύȫ�����ĵ��ļ�
git commit -a 

# �������ط�֧�Ĵ����ύ
git commit --amend 

# �������ط�֧�Ĵ����ύ��������Ϣ
git commit --amend --author="Gene <Gene@genesis.org>" --no-edit
```

### git branch
```console
# �鿴��ǰ�����ȫ����֧
git branch -a  

# �鿴���ط�֧��Զ�̷�֧��ϵ,������֧�����,�ύ״̬�Ա�
git branch -vv
scala         a366fd6 add scala static analysis plugin
scala-lint    a366fd6 [origin/master: ahead 1, behind 12] add scala static analysis plugin
# ������ط�֧û��track remote branch�� ��scala������������һ�����������趨upstream��track״̬
git branch -u origin/master scala
git branch --set-upstream-to=origin/master scala
scala         a366fd6 [origin/master: ahead 1, behind 12] add scala static analysis plugin
scala-lint    a366fd6 [origin/master: ahead 1, behind 12] add scala static analysis plugin
# ȡ��upstream�趨
git branch --unset-upstream scala

# ǿ��ɾ��master��֧
git branch -D master  

# ɾ��master��֧������Ҫ���б����ύ�Ѿ��ϲ���upstream����û�б仯
git branch -d master  

# ��������ǰ��֧�� master-dev
git branch -m master-dev
# ������ master-dev Ϊ master
git branch -m master-dev master

# �鿴��Щ���ط�֧����ĳ���ύ
git branch --contains e96a53a68b2ed1ce9b98661b07f8071e789d2319
# �鿴��Щremote��֧����ĳ���ύ
git branch -r --contains e96a53a68b2ed1ce9b98661b07f8071e789d2319
# �鿴��Щ���ط�֧������ĳ���ύ
git branch --no-contains 45eb3ae0a5ccce683a74625409bd015c1fd6d312
```

### git checkout
```console
# ��glance/stable/icehouse��֧checkout��һ������master��֧
git checkout -b master glance/stable/icehouse

# �ӵ�ǰ��֧�л���master��֧
git checkout master

# ��tag(2014.1.1)��Ӧ�Ŀ���checkout�ɱ��ط�֧tag-dev
# �ֿ�Ŀ��ձ������޸�,ֻ�ܱ�ɱ��ط�֧�����޸�
git checkout -b tag-dev 2014.1.1

# ��ʱ�л���ĳ��commitλ��,�л�����뵱ǰ�ķ�֧����,�����޷�֧checkout״̬
git checkout 0d1d7fc32

# ����ǰ��֧����ع���upstream HEADλ��
git checkout -f  

# �ļ�����untracked, �����ڵ�ǰ��֧�ж�Run.java�ļ����޸�
git checkout -- Run.java  

# ��dev��֧�л�ȡRun.java�����Ǳ��ط�֧�ļ�
git checkout dev -- Run.java  
# ��ĳ��commit�л�ȡRun.java�����Ǳ��ط�֧�ļ�
git checkout 0d1d7fc32 -- Run.java 

# ��dev��֧�л�ȡ./java/srcĿ¼�������ļ����½��򸲸Ǳ��ط�֧./java/src/���ļ�
git checkout dev -- ./java/src/
```

### git tag 
```console
# �鿴��ǰ�����ȫ��tag����
git tag

# �鿴��δ���ɾ����ǩ
git tag --help  

# �鿴commit��������Щ�ֿ��ǩ������
$git tag --contains dbb242b776908ca50ed8557ebfe7cfcd879366c8
```

### git ls-files
```console
# �г���ǰĿ¼����Ŀ¼�������ļ�
git ls-files

# ����Example.java �Ƿ���ڵ�ǰbranch��
git ls-files | grep Example.java

# ͳ�Ƶ�ǰĿ¼����Ŀ¼�������ļ�������
git ls-files | xargs cat | wc -l
```

### git merge rebase
+ [Rewriting History](https://git-scm.herokuapp.com/book/en/v2/Git-Tools-Rewriting-History)<br>
+ rebase�޸��ύ��ʷ[[1](https://mp.weixin.qq.com/s/OW7ZpE2wvhwVOhLBVODHdA), [2](https://mp.weixin.qq.com/s/xLPDiPRzNnLLTrTlWJqWCA)]
+ [rebaseһ���޸Ķ���ύ��¼](https://jacopretorius.net/2013/05/amend-multiple-commit-messages-with-git.html)

```console
# ��������֧(stable/icehouse)�ϲ�����ǰ��֧��
git merge stable/icehouse 

# b1 b2��֧����rebase/merge ��������Ĳ���
master branch : patch1 <- patch2 <- patch3
b1 branch forking from patch2 : patch1 <- patch2 <- patch4
b2 branch forking from patch2 : patch1 <- patch2 <- patch4
# ����֧�ϲ����, merge�������ϲ���, rebase��ԭ��֧˳�򲻻ᱣ��
git log --oneline 
b1 > git merge master : patch1<-patch2<-patch4<-patch3<-auto-merge-patch 'Merge branch 'master' into b1'
                                    ��master��patch2֮���ύ���ϲ���b1��֧��󣬲�����һ���Զ��ϲ��ύ
b2 > git rebase master : patch1<-patch2<-patch3<-patch4
                                    ��master��patch2֮���ύ�����뵽b1��֧patch2֮��

##################################									
# �޸ı����ύ��ʷ��������(������ط�֧�Ѿ�push��remote, �����޸Ļ����������remote repo�ĳ�ͻ)
##################################
# rebase��ǰ��֧������9���ύ����Ĭ��text editor��ʾ�޸��ύ�б�
git rebase -i @~9   
# ��vi�༭���а����޸ĵ�commit״̬��'pick'�ĳ�'e', �����˳�
# ���������ύ˳��, ��vi�༭���е�����Щcommits list��ǰ��˳�򼴿�
# �޸���Ҫ�������ļ���Ȼ����add������Ϊstaged״̬
git add -A
# �ύ�޸�
git commit --amend 
# replay��������ύlist
git rebase --continue  

##################################									
# ����ύ��������
##################################
# ������޸�һ�£�������Ƕ�һ�������reset����
git rebase -i @~9 
# �޸�commit״̬ pick -> edit�������˳�
# ���˵�ǰcommit
git reset @~1
# Ȼ������ѡ����֯�ļ��ύ
git add; git commit
git rebase --continue 

##################################									
# �ϲ�����ύ��������
##################################
# rebase�����ύ
git rebase -i @~3
pick f7f3f6d Change my name a bit
pick 310154e Update README formatting and add blame
pick a5f4a0d Add cat-file
# squash�ǰѺ����ϲ���ǰһ��������commit log message�� �����Լ��޸�
pick f7f3f6d Change my name a bit
squash 310154e Update README formatting and add blame
squash a5f4a0d Add cat-file
# ��commit log message�������ύ��Ϣ�ϲ�����
Change my name a bit

Update README formatting and add blame

Add cat-file

# replay
git rebase --continue  
# fixup��squashһ�����ܣ�������ǲ������ϲ���ȥ��commit log message
pick f7f3f6d Change my name a bit
fixup 310154e Update README formatting and add blame
fixup a5f4a0d Add cat-file
# log message�Ծ��ǵ�һ��
Change my name a bit
```

### git reset revert
```console
# ���������������, ���commitû��push, ��ô�Ϳ����ڱ��ض���, ��reset����
# ����Ѿ�push��remote�Ĳֿ�, ��ô����revert, ��������µ�commitȥundo�Ѵ����ύ

# ��HEAD���˵�ָ���ύλ��, δ�ύ���κα����޸Ķ��ᶪ��
git reset --hard 0d1d7fc32

# ���˵���֧HEAD�ύ��
git reset --hard HEAD

# ����ʱ����δ�ύ�ı��ر��, Ȼ����˵��ύ��, �ٽ���ʱ�����޸�Ӧ�õ��»���
git stash; git reset --hard 0d1d7fc32; git stash pop
# git stash��һ��������������ʱ��������޸ġ�ͨ��tab�������Զ���ʾ
git stash list
git stash push <file>
git stash clear
git stash show

# ͨ����λҲ���Դﵽ��һ������ͬЧ��  
# hard/soft���ַ�ʽ����������Ƿ���δ�ύ���ģ�Ĭ�ϲ�ָ��hard�Ͷ���softģʽ
# �����ѱ���һ���ύ��ĳЩ�ļ�ɾ����������soft reset��Ȼ��������ѡ���ύ
git reset --soft 0d1d7fc32

# ��ָ���ļ����˵�ǰ�����ύ�ĵ�
git reset @~2 -- maven_install.json
# �����ļ����˵�HEAD�ύ��
git reset @

# �������ύ������commit, git�Զ�����һ��revert��commit
git revert a867b4af  25eee4ca 0766c053

# ���˴ӵ�ǰHEAD��ǰ2��commit, git�Զ�����һ��revert��commit
git revert HEAD~2..HEAD

# �����ύ, ���Զ�����revert��commit, �ֶ��ύ
git revert --no-commit 0766c053..HEAD; git commit
```

### git cherry-pick
```console
# �ڵ�ǰ��֧�л��dev��֧commit ID��6d17945cefa���ύ
git cherry-pick dev 6d17945cefa

# �ڵ�ǰ��֧�л�ȡdev��֧���һ���ύ
git cherry-pick dev  

# �ڵ�ǰ��֧�л�ȡdev��֧�����ڶ��ύ
git cherry-pick dev^  

# �ڵ�ǰ��֧�л�ȡdev��֧���������ύ
git cherry-pick dev~2  

# �ڵ�ǰ��֧�л�ȡdev��֧�����ύ
git cherry-pick dev~2 dev^ dev
# ����ͨ��range�趨3��commits��
# range������(a..b], a��b�ɣ�a��ȡ��ȡa����һ����b����commits 
git cherry-pick dev~3..dev
```

### git diff
```console
# �Ƚϵ�ǰmaster��֧��remote��master��֧�Ĳ���
git diff origin/master  
# �Ƚϵ�ǰmaster��֧��remote��master��֧��ָ���ļ��Ĳ���
git diff origin/master  -- Run.java

# �Ƚϱ���myBranch��֧��remote master��֧�Ĳ���
git diff myBranch origin/master  

# �Ƚϱ���myBranch��֧��remote master��֧��ĳ��Ŀ¼����
git diff myBranch origin/master -- src/main/java 

# �鿴�ļ�����ǩ����(0.12.0-0.13.0)֮��Ĳ���
git diff 0.12.0 0.13.0 -- glanceclient/common/http.py  

# �Ƚϵ�ǰ��ָ֧���ļ������ύ��ǰһ���ύ��Ĳ���
git diff @:./java_deps.bzl @^:./java_deps.bzl
```

### git log 
```console
# ��Ҫ����flash-dev��֧�����ύ��־
git log --oneline flash-dev

# ��Ҫ���ܵ�ǰ��֧srcĿ¼��ǰ5����־
git log -5 --oneline  -- src/

# �鿴��ɾ���ļ�·��src/main/java����Ӧ��commit��¼
git log --oneline -- src/main/java ���� git rev-list -n 1 HEAD -- src/main/java
git log f2edbe81730 --summary 

# ����commit�ύ�ļ���Ϣ
git log --stat f2edbe81730

# �鿴src/main/javaĿ¼��ƥ��rpc�ؼ��ֵ������ύ
git log --grep=rpc -- src/main/java

# �������з�֧��ʷ��¼���Ƿ�ƥ��ָ���ļ����൱��find�����find�޷������ǵ�ǰ�����֧
# -- **��PolicyFacadeImplAjIf* ָ����ǰĿ¼����Ƕ����Ŀ¼�в�ѯ�ļ���
# �����git��Ŀ¼��ִ�У�����findȫ������Ŀ¼
git log --all --summary -- "**/PolicyFacadeImplAjIf*"

# �������б�Batmanɾ�����ļ����ύ��¼
git log --diff-filter=D --author=Batman --summary  

# �鿴ƥ��name�����������ύ����
git log --author <name>  

# �鿴ƥ��name��������ָ���ļ�Ŀ¼���ύ����
git log --author <name> ./src-1/test/ ./src-2/test/

# �鿴ƥ��name�����������ύ��Ҫͳ��
git log --author <name> --oneline --shortstat

# �鿴ƥ��name�����������ύ��ɾ��Ҫͳ��
git log --author <name> --oneline --numstat

# �鿴ƥ��name�����������ύ��ͳ��
git shortlog --author <name>
git shortlog -s | grep <name>
git log --author <name> | grep <name> | wc -l 

# �鿴ʱ���(2018ȫ��)�����ύ�ĸ�Ҫ
git shortlog --since=2018 --before=2019

# �鿴ƥ�����ߵ�ȫ����ɾ������ͳ��
git log --author=<name> --pretty=tformat: --numstat | awk \
'{ add += $1; subs += $2; loc += $1 - $2 } END \
{ printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'

# ���ܵ�ǰ��֧������tag����֮��������ύ��־
git log tag1..tag2
# ��������֧֮����ύ����
git log master..origin/master
```

### git rev-list
```console
# ͳ��ָ���ύ��HEAD֮���ύ����
git rev-list a9e5e1dc6afd..@ --count

# ͳ��������֧�ύ��Ŀ��
git branch -vv
  im-trace  4ed5411 [origin/xbranch: ahead 7, behind 1059] 
* uatrace  1fdddc9 [origin/xbranch: behind 61]

# �鿴uatrace&im-trace���xbranch֮��behind�ύ��Ŀ��ֵ 1059-61=998
git rev-list @ ^im-trace --count
998
# �鿴uatrace&im-trace���xbranch֮��ahead�ύ��Ŀ��ֵ 7-0=7
git rev-list @..im-trace --count
7

# �Ƚ������ύ�ڷ�֧�ϵ��Ⱥ�������ִ�˵���ύ��ǰ��Ҳ����1fdddc9Ϊbf5126�汲
git rev-list bf5126..@ --count
29
git rev-list 1fdddc9..@ --count
62
# ���ӱ����汲ͳ�ƣ��޽�����
git rev-list bf5126..1fdddc9 --count
# ���汲���ӱ�ͳ�ƣ�62-29=33
git rev-list 1fdddc9..bf5126 --count
33
# ��ͳ��������г�����������ύhash
git rev-list 1fdddc9..bf5126
bf5126b58f5b49933efeadee3fbb6689e91bec2b
...
e786c78182a1c51cb7bcf75ff71fd9d6b69b1f09

# ͳ�Ƶ�ǰbranchȫ���ύ����
git rev-list --count HEAD
# ͳ��ʱ����ڵ�ǰbranchȫ���ύ������
git rev-list --count @ --since="Dec 3 2015"  --before="Jan 3 2016"
# ͳ��ʱ���������branchȫ���ύ������
git rev-list --count --since="Dec 3 2015"  --before="Jan 3 2016" --all
# �鿴ʱ���������branchȫ���ύ������,������merge-commits��Ŀ
git rev-list --count --since="Dec 3 2015"  --before="Jan 3 2016" --all --no-merges
```

### git blame
```console
# �鿴http.py�ļ�ÿһ���޸ļ�¼
git blame glanceclient/common/http.py  
```

### git show
```console
# ��ʾmaster��֧���һ���ύ������
git show master @
# ��ʾmaster��֧�����ڶ����ύ������
git show master^ 
# ��ʾmaster��֧�����������ύ������
git show master~2
# ��ʾmaster��֧�������ĸ��ύ������
git show master^~2

# �鿴ĳ���ύ����Щ�ļ����޸�
git show e96a53a68b2ed1ce
# �鿴ĳ���ύ��test.java����
git show e96a53a68b2ed1ce:src/test.java

# �鿴dev��֧��test.java����
git show dev:test/src/test.java

# �鿴dev��֧��srcĿ¼������
# ֧��tab��ʾ����
git show dev:test/src/
# ls-tree�ṩ���ƹ���
git ls-tree --full-tree -r --name-only dev | less
# ls-files�ṩ��ǰ����branch���ļ��б�
git ls-files

# ��dev��֧��test.java���뵱ǰ��֧��
mkdir -p test/src; git show dev:test/src/test.java > ~/test/src/test.java
```

### git format-patch
```console
# �ڵ�ǰ��֧��Ϊ���µ�һ���ύ(head -1)�������
git format-patch -1 HEAD  

# ��dev��֧���µ�һ���ύ(head -1)�������
git format-patch -1 dev  

# ����ǰ��֧��f5f8ff546b3��ʼ��HEAD�����ύ�������
git format-patch f5f8ff546b3  

# ����ǰ��֧f5f8ff546b3�ύ�������
git format-patch -1 f5f8ff546b3  
```

### git apply-patch
```console
# �鿴����������
git apply --stat my.patch
# ���patch�Ƿ���ڴ���
git apply --check my.patch
# Ӧ��patch����unstaged changes
git apply my.patch

#Ӧ��patch����һ��commit
git am my.patch
```

### git grep
```console
# ������ǰbranch��ǰĿ¼��ƥ�������
# ����repo�����������������ٶ�Զ����grep
# repo��������״��û��-r������Ĭ��ȫ·������
git grep -in 'if('

# �������branch
git grep -in 'Converter' origin/master origin/dev
# �����ǰ����Ŀ¼��project/src/main/java����������ִ��Ŀ¼��ͬ����������
git grep -in 'Converter' origin/master:src/main/java origin/dev:src/main/java

# �൱��grep�� --include����
# �����治��ָ��·�������������
git grep -in 'if(' -- '*.java' '*.cpp'
```

### git gc
```console
# �������к� .gitĿ¼��ռ�ô����ռ�,����ͨ��gc�����տռ�
git gc --aggressive --prune
```

### gitk 
```console
# GUI��ʽ�鿴��֧�޸ļ�¼
gitk

# �鿴�ļ��޸ļ�¼
gitk file
```

---

### [git review](https://osm.etsi.org/wikipub/index.php/Using_git-review_to_push_and_review_changes)
1. ��װ֧��git��reviewtר����� (http://en.wikipedia.org/wiki/List_of_tools_for_code_review)

2. ͨ��gerrit review������������, ����ͨ��git-review����

	- git-review��openstack��֯�������׵�. ��Ҫ���ⰲװ
	```console
	sudo pip install git-review
	```
	Ubuntuƽ̨
	```console
	aptitude install git-review
	```
	Macƽ̨
	```console
	brew install git-review
	```
	- ����review�����ļ�, ��Ҫ�ڱ���git repo�ĸ�Ŀ¼, ��.gitͬһ��Ŀ¼, ����һ��.gitreview�������ļ�
	```console
	touch .gitreview
	```
	- .gitreview�����ļ�����Windows .ini�ļ���ʽ�����Gerrit��װ�������Ϣ
	ģ������:
	```console
	[gerrit]
	scheme=ssh   
	host=gitreview.example.com
	port=29418
	project=XXX.git
	defaultbranch=master
	```
	- �趨Ĭ��rebase��zero ����ʹgit-review����rebase changes (same as the -R command line option)
	```console
	[gerrit]
	scheme=ssh   --default scheme (ssh/http/https) of gerrit remote
	host=review.example.com  --gerrit remote server ���Բ鿴.git/config������
	port=29418
	project=department/project.git   --��Ӧ���ļ��ֿ�
	defaultbranch=master
	defaultremote=review
	defaultrebase=0
	track=0
	```   
   - ����ȫ��review�û���
	```console
	git config -l    -- list all current config
	# update author/email per repo, useful for current repo config
	git config user.name "Gene"
	git config user.email "Gene@genesis.org"
	
	# update global author/email�� impact on all git repo
	git config --global user.name "Gene"
	git config --global user.email "Gene@genesis.org"
	
	#remove global config
	git config --global --unset-all user.name
	```
	*����ͬ����ͨ��.gitreview�����ļ����������ͬʱ��Чʱ�������в�������*

    - �趨commit-msg���Զ�����Change-Id (https://git.eclipse.org/r/Documentation/cmd-hook-commit-msg.html)
	```console
	$ scp -p -P 29418 king@review.example.com:hooks/commit-msg ~/source/.git/hooks/
	```
	��
	```console
	$ curl -Lo ~/source/.git/hooks/commit-msg https://review.example.com/tools/hooks/commit-msg
    ```	
	*�������Ȩ�����⣬һ����gerrit��������û�б��汾����Կ����Ҫͨ��ssh-keygen�������~/.ssh/id_rsa.pub�������Ƶ�gerrit��������ϡ�
	��֮��õ���commit-msg�ļ�û��ִ��Ȩ�ޣ�����Ҫ�޸�һ��ִ��Ȩ��*
	```console
	$ chmod u+x ~/source/.git/hooks/commit-msg
	```
	Ok���޸�commit������֮��ͻ����ύ��Ϣ�в���һ��Change-ID��, ��Change-Id: I5b8ecff9d0b6dddda4c76e162629017ac5026341

    - git review �����ο�
	�ڱ���repo�ύcommit�󣬼���ͨ�� git review����review request, ���� git review branchName ����ָ����֧��review request
	<br>���½�����:<br>
		
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
	- �����в�������˵���ο� man git-review
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


		   
