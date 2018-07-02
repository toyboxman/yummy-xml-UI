### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is git commands used usually*

---

* ����git�û�����Ϣ
```bash
# listȫ������
git config -l

# ���õ�ǰgit�û������ʼ����ԣ��������ڵ�ǰgit�ֿ�
git config user.name "Gene"
git config user.email "Gene@genesis.org"

# ����ȫ����git�û������ʼ����ԣ���������ȫ��git�ֿ�
git config --global user.name "Gene"
git config --global user.email "Gene@genesis.org"

# remove global config
git config --global --unset-all user.name
```
* ��ʼ��һ���յı���git�ֿ�
```bash
git init
```
* ֱ�ӿ�¡һ��Զ�˴���ֿ⵽����
```bash
$git clone https://github.com/openstack/glance.git
```
* �ڲֿ������һ��Զ�̴���Դ
```bash
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

* ���´���ֿ�
```bash
# ���±���Զ�̴���Դ,���Ḳ�Ǳ��ط�֧
git remote update
# ��ȡԶ�˴���������µĴ��룬���Ҹ��Ǳ��ط�֧
git pull
```

* �ύ���뵽Զ�̴����
```bash
# �ѱ��ص�ǰ��֧�ύ��remoteӳ��ķ�֧��
git push

# �ѱ���glanceĿ¼������push��remote��icehouse��֧
git push glance HEAD:refs/heads/icehouse

# EugeneĿ¼��master��֧�����ڣ�����ʧ��
git push Eugene master:refactor/testcases   
error: src refspec master does not match any.
error: failed to push some refs to 'https://github.com/toyboxman/incubator-griffin.git'
# ����EugeneĿ¼��m0��֧��remote��refactor/testcases��֧�ɹ�
git push Eugene m0:refactor/testcases
To https://github.com/toyboxman/incubator-griffin.git
 * [new branch]      m0 -> refactor/testcases
```

* ����һ���ɹ������������ص�remote repo
```bash
# ����remoteָ��Ŀ¼��initһ���ֿ�
# ���ش���һ��Զ��git repoӳ���alias
git remote add myAppName ssh://192.168.149.128/~/source/app/.git 

# ������myAppNameĿ¼��master��֧���͵�remote repo
git push myAppName master
```

* �鿴�ֿ����ļ�״̬
```bash
git status
```
* �ڱ��ط�֧������ļ���Ŀ¼
```bash
# ��source�������ļ���Ŀ¼��������ύstaged״̬
git add ./source 

# ���ֲ˵���ʽ�鿴��ǰ���д��ύ�ļ�״̬
git add -i  

# �������޸��ļ�������ύstaged״̬
git add --all  
```

* �ڱ��ط�֧��ɾ���ļ���Ŀ¼
```bash
# ��source�������ļ���Ŀ¼���Ӵ��ύstaged״̬�ĳ�untracked״̬
git rm --cached ./source
```

* �ύ�����������ط�֧��
```bash
# �ύȫ�����ĵ��ļ�
git commit -a 

# �������ط�֧�Ĵ����ύ
git commit --amend 

# �������ط�֧�Ĵ����ύ��������Ϣ
git commit --amend --author="Gene <Gene@genesis.org>" --no-edit
```

* ������֧����
```bash
# �鿴��ǰ�����ȫ����֧
git branch -a  

# �鿴���ط�֧��Զ�̷�֧��ϵ,������֧�����,�ύ״̬�Ա�
git branch -vv

# ǿ��ɾ��master��֧
git branch -D master  

# ɾ��master��֧������Ҫ���б����ύ�Ѿ��ϲ���upstream����û�б仯
git branch -d master  

# ��������ǰ��֧�� master-dev
git branch -m master-dev

# �鿴ĳ���ύ������Щ��֧��
git branch --contain e96a53a68b2ed1ce9b98661b07f8071e789d2319
```

* ������֧checkout����
```bash
# ��glance/stable/icehouse��֧checkout��һ������master��֧
git checkout master glance/stable/icehouse

# �ӵ�ǰ��֧�л���master��֧
$git checkout master 

# ��ʱ�л���ĳ��commitλ��,�л�����뵱ǰ�ķ�֧����,�����޷�֧checkout״̬
git checkout 0d1d7fc32

# ����ǰ��֧����ع���upstream HEADλ��
git checkout -f  

# �����ڵ�ǰ��֧�ж�Run.java�ļ����޸�,��ʱ�ļ�����δ�ύ״̬
git checkout -- Run.java  
```

* �鿴��ǰ�����ȫ��tag����
```bash
git tag
# �鿴��δ���ɾ����ǩ
$git tag --help  
```

* ��tag��Ӧ�Ŀ���checkout�ɱ��ط�֧���ֿ���ձ����޷��޸ģ�ֻ�ܱ�ɱ��ط�֧���ܸ�
```bash
git checkout -b branch_name tag_name
$git checkout -b branch1 2014.1.1
```

* ��ǰ���ļ������ļ���Ϣ����
```bash
# �г���ǰĿ¼����Ŀ¼�������ļ�
$git ls-files

# ͳ�Ƶ�ǰĿ¼����Ŀ¼�������ļ�������
$git ls-files | xargs cat | wc -l
```

* �ϲ�����֧���룬��rebase������Щ���������ڷ�֧���ڵ���
```bash
git merge branch_a   
# ��������֧�ϲ�����ǰ��֧��
$git merge stable/icehouse 
```
* �ϲ���֧���� rebase��merge
```bash
master branch : patch1<-patch2<-patch3
b1 branch from patch2 : patch1<-patch2<-patch4
b2 branch from patch2 : patch1<-patch2<-patch4
# ����֧�ϲ����
git log --oneline 
b1-git merge master : patch1<-patch2<-patch4<-patch3<-auto-merge-patch 'Merge branch 'master' into b1'
                                    ��master�������ύ�鲢��b1��֧��󣬲�����һ���Զ��ϲ��ύ
b2-git rebase master : patch1<-patch2<-patch3<-patch4 �鲢��master��֧�У���������ύ����
```

* �޸ı������ύ����ʷ
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
* ���˱��,�ύ
```bash
# ���������������,���commitû��publish��ȥ����ô�Ϳ����ڱ��ض�������reset����
# ����Ѿ�publish������repo�ˣ���ô����revert����������µ�commitȥundo�Ѵ����ύ
git reset/revert                      
```
* ��HEAD���˵�ָ���ύλ�ã�δ�ύ���κα��ظı䶼�ᶪ��
```bash
$git reset --hard 0d1d7fc32
```
* ���߻��˵���֧�����ύ��
```bash
$git reset --hard HEAD
```
* ����ʱ����δ�ύ�ı��ر����Ȼ����˵��ύ�㣬�ٽ���ʱ�����޸�Ӧ�õ��»��㡣
```bash
$git stash; git reset --hard 0d1d7fc32; git stash pop
```
* ͨ����λҲ���Դﵽ��һ������ͬЧ��  hard/softǰ�����ַ�ʽ����������Ƿ���δ�ύ����
```bash
$git reset --soft 0d1d7fc32
```
* �������ύ������commit
```bash
# �Զ�����һ��revert��commit
$git revert a867b4af 25eee4ca 0766c053
```
* ���˴ӵ�ǰHEAD��ǰ2��commit
```bash
$git revert HEAD~2..HEAD
```
* �����ύ�����Զ�����revert��commit���ֶ��ύ
```bash
$git revert --no-commit 0766c053..HEAD; git commit
```
* �鿴ĳ���ύ����Щ�ļ����޸�
```bash
$git show e96a53a68b2ed1ce9b98661b07f8071e789d2319
```

* �ڵ�ǰ�����֧�кϲ�ĳ���ύ����
```bash
git cherry-pick -x
```
* �ڵ�ǰ�����֧�кϲ�������֧ĳ���ύ����
```bash
git cherry-pick branch
# ��ȡdev��֧���һ���ύ
$git cherry-pick dev  

# ��ȡdev��֧�����ڶ��ύ
$git cherry-pick dev^  

# ��ȡdev��֧���������ύ
$git cherry-pick dev~2  
```

* �Ƚϱ��ط�֧��remote��֧����
```bash
# �Ƚϱ���master��֧��remote master��֧�Ĳ���
$git diff origin/master  

# �Ƚϱ���myBranch��֧��remote master��֧�Ĳ���
$git diff myBranch origin/master  
```
* �Ƚ�ĳ���ļ��ڲ�ͬtag����������
```bash
git diff tag1 tag2  -- filename

# �鿴�ļ�����ǩ���޸�
$git diff 0.12.0 0.13.0 -- glanceclient/common/http.py  
```
* �鿴�޸��ύ��������Щ��ǩ��
```bash
git tag --contains commitID
$git tag --contains dbb242b776908ca50ed8557ebfe7cfcd879366c8
```
* �鿴ĳ�������֧�����ύ��־summary
```bash
git log --oneline <branch>
# �鿴��ǰ��֧��־�б�
$git log --oneline  
```

* �鿴��֧��ĳ�����ߵ��ύ�б�
```bash
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

# �鿴ƥ�����ߵ�ȫ����ɾ������ͳ��
git log --author=<name> --pretty=tformat: --numstat | awk \
'{ add += $1; subs += $2; loc += $1 - $2 } END \
{ printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs, loc }'
```

* �鿴�����֧������tag����֮��������ύ��־
```bash
git log tag1..tag2
```
* �鿴ĳ���ļ��������޸�ϸ��
```bash
git blame filename
# �鿴http.py�ļ�ÿһ���޸ļ�¼
$git blame glanceclient/common/http.py  
```
* ����Դ�����ļ�����
```bash
# �ڵ�ǰ��֧��Ϊ���µ�һ���ύ(head -1)�������
$git format-patch -1 HEAD  

# �ڵ�ǰ��֧��Ϊĳһ���ύ(sha -1)�������
$git format-patch -1   
```

* GUI��ʽ�鿴��֧�޸ļ�¼
```bash
gitk
gitk file
```

---

### �����review
1. ��װ֧��git��reviewtר����� (http://en.wikipedia.org/wiki/List_of_tools_for_code_review)

2. ͨ��gerrit review������������, ����ͨ��git-review����

	- git-review��openstack��֯�������׵�. ��Ҫ���ⰲװ
	```bash
	sudo pip install git-review
	```
	��
	```bash
	aptitude install git-review
	```
	- ����review�����ļ�, ��Ҫ�ڱ���git repo�ĸ�Ŀ¼, ��.gitͬһ��Ŀ¼, ����һ��.gitreview�������ļ�
	```bash
	touch .gitreview
	```
	- .gitreview�����ļ�����Windows .ini�ļ���ʽ�����Gerrit��װ�������Ϣ
	ģ������:
	```bash
	[gerrit]
	scheme=ssh   
	host=gitreview.example.com
	port=29418
	project=XXX.git
	defaultbranch=master
	```
	- �趨Ĭ��rebase��zero ����ʹgit-review����rebase changes (same as the -R command line option)
	```bash
	[gerrit]
	scheme=ssh   --default scheme (ssh/http/https) of gerrit remote
	host=review.example.com  --gerrit remote server
	port=29418
	project=department/project.git   --��Ӧ���ļ��ֿ�
	defaultbranch=master
	defaultremote=review
	defaultrebase=0
	track=0
	```   
   - ����ȫ��review�û���
	```bash
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
	```bash
	$ scp -p -P 29418 king@review.example.com:hooks/commit-msg ~/source/.git/hooks/
	```
	��
	```bash
	$ curl -Lo ~/source/.git/hooks/commit-msg https://review.example.com/tools/hooks/commit-msg
    ```	
	*�������Ȩ�����⣬һ����gerrit��������û�б��汾����Կ����Ҫͨ��ssh-keygen�������~/.ssh/id_rsa.pub�������Ƶ�gerrit��������ϡ�
	��֮��õ���commit-msg�ļ�û��ִ��Ȩ�ޣ�����Ҫ�޸�һ��ִ��Ȩ��*
	```bash
	$ chmod u+x ~/source/.git/hooks/commit-msg
	```
	Ok���޸�commit������֮��ͻ����ύ��Ϣ�в���һ��Change-ID��, ��Change-Id: I5b8ecff9d0b6dddda4c76e162629017ac5026341

    - git review �����ο�
	�ڱ���repo�ύcommit�󣬼���ͨ�� git review����review request, ���� git review branchName ����ָ����֧��review request
	<br>���½�����:<br>
		
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
	- �����в�������˵���ο� man git-review
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


		   
