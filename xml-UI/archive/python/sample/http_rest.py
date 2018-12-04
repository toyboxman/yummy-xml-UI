# 导入前可以用pip命令查看依赖包有没有本地安装
# pip list | grep requests
import requests
from requests import HTTPError
import json

# requests在python3 3.4版本后支持使用headers来指定http参数
# verify指定是否忽略ssl证书检查
headers = {'Content-type': 'application/json', 'Authorization': 'Basic YWRtaW46ZGVmYXVsdA==', 'Accept': 'application/json'}
r = requests.get('https://10.40.72.6/api/2.0/vdn/controller', headers=headers, verify=False)
clist = r.json()
#print(clist)

def get_controllers():
    url = "https://10.40.72.6/api/2.0/vdn/controller"
    response = requests.get(url, headers=headers, verify=False)
    if response:
        # response的body
		print(response.content)
        return response.json()

    print("Failed to get controller cluster list.")
    
def get_controller_ids():
    controllers = get_controllers()
    # parse json response:
    id_list = []
    for ctl in controllers["controllers"]:
        id_list.append(ctl["id"])
    return id_list    

vtep_obj = {}
for ctl in get_controller_ids():
    cli_param = {}
    cli_param["name"] = "vni"
    cli_param["value"] = 5102
    cli_param_ctl = {}
    cli_param_ctl["name"] = "target"
    cli_param_ctl["value"] = ctl
    post_body = {}
    post_body["cliCommandKey"] = "getVtepTableFromController"
    post_body["cliParams"] = {}
    post_body["cliParams"]["cliParamList"] = [cli_param, cli_param_ctl]
    print(post_body)
	# python的dictionary中key/value使用单引号，json解析时候识别失败
	# 需要通过转化变成双引号
    print(json.dumps(post_body))
    url = "https://10.40.72.6/api/2.0/vdn/controller?action=execute&cmd-module=vxlan"
    try:
        resp = requests.post(url, data=json.dumps(post_body), headers=headers, verify=False)
        print(resp.content)
    except HTTPError as ex:
        print(ex)
    vteps = [vtep.split("|") for vtep in resp.content.decode().split("\n")[1:-2]]
    print(vteps)
    
    for vtep in vteps:
        vtep_obj[vtep[1]] = {}
        vtep_obj[vtep[1]]["vtep_ip"] = vtep[1]
        vtep_obj[vtep[1]]["segment_id"] = vtep[2]
        vtep_obj[vtep[1]]["vtep_mac_address"] = vtep[3]
print(vtep_obj)
# python中list dictionary数据结构都可以直接使用在ifelse中
# 数据体empty就是false，非空就是true
if not vtep_obj:
    print("Dict is Empty")

