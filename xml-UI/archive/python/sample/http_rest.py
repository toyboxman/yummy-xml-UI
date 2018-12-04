# ����ǰ������pip����鿴��������û�б��ذ�װ
# pip list | grep requests
import requests
from requests import HTTPError
import json

# requests��python3 3.4�汾��֧��ʹ��headers��ָ��http����
# verifyָ���Ƿ����ssl֤����
headers = {'Content-type': 'application/json', 'Authorization': 'Basic YWRtaW46ZGVmYXVsdA==', 'Accept': 'application/json'}
r = requests.get('https://10.40.72.6/api/2.0/vdn/controller', headers=headers, verify=False)
clist = r.json()
#print(clist)

def get_controllers():
    url = "https://10.40.72.6/api/2.0/vdn/controller"
    response = requests.get(url, headers=headers, verify=False)
    if response:
        # response��body
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
	# python��dictionary��key/valueʹ�õ����ţ�json����ʱ��ʶ��ʧ��
	# ��Ҫͨ��ת�����˫����
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
# python��list dictionary���ݽṹ������ֱ��ʹ����ifelse��
# ������empty����false���ǿվ���true
if not vtep_obj:
    print("Dict is Empty")

