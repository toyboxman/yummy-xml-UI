### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)

---

### *Following content is git commands used usually*

---

* ����git�û�����Ϣ
```shell
git config --global user.name "XWXW"
git config --global user.email "XXXX@YYY.com"
```
* ��ʼ��һ���յı���git�ֿ�
```shell
git init
```
* ֱ�ӿ�¡һ��Զ�˴���ֿ⵽����
```shell
$git clone https://github.com/openstack/glance.git
```
* �ڲֿ������һ��Զ�̴���Դ
```shell
$git remote add glance https://github.com/openstack/glance.git
```
* ����һ���ɹ������������ص�remote repo
```shell
$git remote add myAppName ssh://192.168.149.128/~/source/app/.git ����һ��myAppName��Ӧ��Զ��git repoӳ��
$git push myAppName master  ������repo���͵�remote repo��master��֧
```
* �鿴Զ��repo
```shell
git remote show
$git remote show appName
```
* ����Զ�̴���Դ
```shell
git remote update
```
* ��Զ�˴�����л�ȡ���µķ�֧���룬���Ǳ��ط�֧
```shell
git pull
```
* �鿴�ֿ����ļ�״̬
```shell
git status
```
* �ڱ��ط�֧������ļ���Ŀ¼
```shell
$git add ./source ��source�������ļ���Ŀ¼��������ύstaged״̬
$git add -i  ���ֲ˵���ʽ�鿴��ǰ���д��ύ�ļ�״̬
```
* �ύ�����������ط�֧��
```shell
git commit
$git commit -a �ύȫ�����ĵ��ļ�
$git commit --amend �������ط�֧�Ĵ����ύ
```
* �鿴��ǰ�����ȫ����֧
```shell
$git branch -a  
```
* �鿴���ط�֧��Զ�̷�֧��ϵ,������֧�����,�ύ״̬�Ա�
```shell
$git branch -vv
```
* ��ĳ����֧checkout��һ�����ط�֧
```shell
git checkout -b local_name branch_name
$git checkout master glance/stable/icehouse
```
* ɾ����֧
```shell
$git branch -D master  ǿ��ɾ��master��֧
$git branch -d master  ɾ��master��֧������Ҫ���б����ύ�Ѿ��ϲ���upstream����û�б仯
```
* �л���ĳһ�����ط�֧
```shell
$git checkout master  �ӵ�ǰ��֧�л���master��֧
```
* ��ʱ�л���ָ���ύλ�ã����뵱ǰ�ķ�֧HEAD���룬�����޷�֧checkout̬
```shell
$git checkout 0d1d7fc32
```
* �鿴��ǰ�����ȫ��tag����
```shell
git tag
$git tag --help  �鿴��δ���ɾ����ǩ
```
* ��tag��Ӧ�Ŀ���checkout�ɱ��ط�֧���ֿ���ձ����޷��޸ģ�ֻ�ܱ�ɱ��ط�֧���ܸ�
```shell
git checkout -b branch_name tag_name
$git checkout -b branch1 2014.1.1
```
* �������ط�֧�����޸�
```shell
$git checkout -f  ������֧����ع���upstream HEAD
$git checkout -- Run.java  ����Run�ļ����޸�
```
* �ڵ�ǰ���ļ����������ļ���������
```shell
$git ls-files | xargs cat | wc -l
```
* �ڵ�ǰ���ļ����������ļ�����������ϸ��Ϣ
```shell
git ls-files | xargs wc -l
```
* �ϲ�����֧���룬��rebase������Щ���������ڷ�֧���ڵ���
```shell
git merge branch_a   
$git merge stable/icehouse ��������֧�ϲ�����ǰ��֧��
```
* �ϲ���֧���� rebase��merge
```shell
master branch : patch1<-patch2<-patch3
b1 branch from patch2 : patch1<-patch2<-patch4
b2 branch from patch2 : patch1<-patch2<-patch4
git log --oneline ����֧�ϲ����
b1-git merge master : patch1<-patch2<-patch4<-patch3<-auto-merge-patch 'Merge branch 'master' into b1'
                                                                                  ��master�������ύ�鲢��b1��֧��󣬲�����һ���Զ��ϲ��ύ
b2-git rebase master : patch1<-patch2<-patch3<-patch4 �鲢��master��֧�У���������ύ����
```
* �ύ���뵽Զ�̴����
```shell
git push
```
* ����ָ�������ύ��ĳ����֧
```shell
$git push glance HEAD:refs/heads/icehouse
```
* �޸ı������ύ����ʷ
> [Link1](https://git-scm.herokuapp.com/book/en/v2/Git-Tools-Rewriting-History)
> [Link2](https://jacopretorius.net/2013/05/amend-multiple-commit-messages-with-git.html)
```shell
$git rebase -i @~9   # Show the last 9 commits in a text editor, @ is shorthand for HEAD, and ~ is the commit before the specified commit
                                   #in a text editor change 'pick' to 'e' (edit), and save and close the file. Git will rewind to that commit
$git add -A
$git commit --amend #make changes
$git reset @~  #discard the last commit, but not the changes to the files
$git rebase --continue  #Git will replay the subsequent changes on top of your modified commit
```
* ���˱��,�ύ
```shell
git reset/revert   ---���������������,���commitû��publish��ȥ����ô�Ϳ����ڱ��ض�������reset���ɣ�
                                  ����Ѿ�publish������repo�ˣ���ô����revert����������µ�commitȥundo�Ѵ����ύ
```
* ��HEAD���˵�ָ���ύλ�ã�δ�ύ���κα��ظı䶼�ᶪ��
```shell
$git reset --hard 0d1d7fc32
```
* ���߻��˵���֧�����ύ��
```shell
$git reset --hard HEAD
```
* ����ʱ����δ�ύ�ı��ر����Ȼ����˵��ύ�㣬�ٽ���ʱ�����޸�Ӧ�õ��»��㡣
```shell
$git stash; git reset --hard 0d1d7fc32; git stash pop
```
* ͨ����λҲ���Դﵽ��һ������ͬЧ��  hard/softǰ�����ַ�ʽ����������Ƿ���δ�ύ����
```shell
$git reset --soft 0d1d7fc32
```
* �������ύ������commit
```shell
$git revert a867b4af 25eee4ca 0766c053
```
* ���˴ӵ�ǰHEAD��ǰ2��commit
```shell
$git revert HEAD~2..HEAD
```
* �����ύ�����Զ�����undo��commit���ֶ��ύ
```shell
$git revert --no-commit 0766c053..HEAD; git commit
```
* �鿴ĳ���ύ����Щ�ļ����޸�
```shell
$git show e96a53a68b2ed1ce9b98661b07f8071e789d2319
```
* �鿴ĳ���ύ����Щ��֧��
```shell
$git branch --contain e96a53a68b2ed1ce9b98661b07f8071e789d2319
```
* �ڵ�ǰ�����֧�кϲ�ĳ���ύ����
```shell
git cherry-pick -x
```
* �ڵ�ǰ�����֧�кϲ�������֧ĳ���ύ����
```shell
git cherry-pick branch
$git cherry-pick dev  --��ȡdev��֧���һ���ύ
$git cherry-pick dev^  --��ȡdev��֧�����ڶ��ύ
$git cherry-pick dev~2  --��ȡdev��֧���������ύ
```
* �Ƚϱ��ط�֧��remote��֧����
```shell
$git diff origin/master  --�Ƚϱ���master��֧��remote master��֧�Ĳ���
$git diff myBranch origin/master  --�Ƚϱ���myBranch��֧��remote master��֧�Ĳ���
```
* �Ƚ�ĳ���ļ��ڲ�ͬtag����������
```shell
git diff tag1 tag2  -- filename
$git diff 0.12.0 0.13.0 -- glanceclient/common/http.py  --�鿴�ļ�����ǩ���޸�
```
* �鿴�޸��ύ��������Щ��ǩ��
```shell
git tag --contains commitID
$git tag --contains dbb242b776908ca50ed8557ebfe7cfcd879366c8
```
* �鿴��֧��ĳ�����ߵ��ύ�б�
```shell
git log --author name  --�鿴ƥ��name�����������ύ
```
* �鿴ĳ�������֧�����ύ��־summary
```shell
git log --oneline branch
$git log --oneline  --�鿴��ǰ��֧��־�б�
```
* �鿴�����֧������tag����֮��������ύ��־
```shell
git log tag1..tag2
```
* �鿴ĳ���ļ��������޸�ϸ��
```shell
git blame filename
$git blame glanceclient/common/http.py  --�鿴http.py�ļ�ÿһ���޸ļ�¼
```
* ����Դ�����ļ�����
```shell
$git format-patch -1 HEAD  --�ڵ�ǰ��֧��Ϊ���µ�һ���ύ(head -1)�������
$git format-patch -1   --�ڵ�ǰ��֧��Ϊĳһ���ύ(sha -1)�������
```
* GUI��ʽ�鿴��֧�޸ļ�¼
```shell
gitk
gitk file
```

---

### �����review
1. ��װ֧��git��reviewtר����� (http://en.wikipedia.org/wiki/List_of_tools_for_code_review)

2. ͨ��gerrit review������������, ����ͨ��git-review����

	- git-review��openstack��֯�������׵�. ��Ҫ���ⰲװ
	```shell
	sudo pip install git-review
	```
	��
	```shell
	aptitude install git-review
	```
	- ����review�����ļ�, ��Ҫ�ڱ���git repo�ĸ�Ŀ¼, ��.gitͬһ��Ŀ¼, ����һ��.gitreview�������ļ�
	```shell
	touch .gitreview
	```
	- .gitreview�����ļ�����Windows .ini�ļ���ʽ�����Gerrit��װ�������Ϣ
	ģ������:
	```shell
	[gerrit]
	scheme=ssh   
	host=gitreview.example.com
	port=29418
	project=XXX.git
	defaultbranch=master
	```
	- �趨Ĭ��rebase��zero ����ʹgit-review����rebase changes (same as the -R command line option)
	```shell
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
	```shell
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
	```shell
	$ scp -p -P 29418 king@review.example.com:hooks/commit-msg ~/source/.git/hooks/
	```
	��
	```shell
	$ curl -Lo ~/source/.git/hooks/commit-msg https://review.example.com/tools/hooks/commit-msg
    ```	
	*�������Ȩ�����⣬һ����gerrit��������û�б��汾����Կ����Ҫͨ��ssh-keygen�������~/.ssh/id_rsa.pub�������Ƶ�gerrit��������ϡ�
	��֮��õ���commit-msg�ļ�û��ִ��Ȩ�ޣ�����Ҫ�޸�һ��ִ��Ȩ��*
	```shell
	$ chmod u+x ~/source/.git/hooks/commit-msg
	```
	Ok���޸�commit������֮��ͻ����ύ��Ϣ�в���һ��Change-ID��, ��Change-Id: I5b8ecff9d0b6dddda4c76e162629017ac5026341

    - git review �����ο�
	�ڱ���repo�ύcommit�󣬼���ͨ�� git review����review request, ���� git review branchName ����ָ����֧��review request
			<br>���½�����:<br>
			```shell
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

			 Gerrit looks up both name of the author and the topic name from Gerrit to name a local branch. This facilitates easier identification of changes.

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
		```shell
		CONFIGURATION
			 This utility can be configured by adding entries to Git configuration.

			 The following configuration keys are supported:

			 gitreview.username
						   Default username used to access the repository. If not specified in the Git configuration, Git remote or .gitreview file, the user will be prompted
						   to specify the username.

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

			 gitreview.track
						   Determines whether to prefer the currently-tracked branch (if any) and the branch against which the changeset was submitted to Gerrit (if there is
						   exactly one such branch) to the defaultremote and defaultbranch for submitting and rebasing against.  If the local topic branch is tracking a remote
						   branch, the remote and branch that the local topic branch is tracking should be used for submit and rebase operations, rather than the defaultremote
						   and defaultbranch.

						   When downloading a patch, creates the local branch to track the appropriate remote and branch in order to choose that branch by default when submit-
						   ting modifications to that changeset.

						   A value of 'true' or 'false' should be specified.

						   true          Do prefer the currently-tracked branch (if any) - equivalent to setting --track when submitting changes.

						   false         Ignore tracking branches - equivalent to setting --no-track (the default) or providing an explicit branch name when submitting
										 changes. This is the default value unless overridden by .gitreview file, and is implied by providing a specific branch name on the
										 command line.

			gitreview.rebase
						   This setting determines whether changes submitted will be rebased to the newest state of the branch.

						   A value of 'true' or 'false' should be specified.

						   false         Do not rebase changes on submit - equivalent to setting -R when submitting changes.

						   true          Do rebase changes on submit. This is the default value unless overridden by .gitreview file.

						   This setting takes precedence over repository-specific configuration in the .gitreview file.

			 color.review  Whether to use ANSI escape sequences to add color to the output displayed by this command. Default value is determined by color.ui.

						   auto or true  If you want output to use color when written to the terminal (default with Git 1.8.4 and newer).

						   always        If you want all output to use color

						   never or false
										 If you wish not to use color for any output. (default with Git older than 1.8.4)
		```								


		   
