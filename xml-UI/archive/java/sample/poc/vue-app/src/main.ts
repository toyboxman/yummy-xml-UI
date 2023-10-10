import { createApp} from 'vue'
import router from './router'
import store from './store'
import App from './App.vue'
import Antd from "ant-design-vue";
import { Button,Form, Field, CellGroup, Cell } from 'vant';
import 'vant/lib/index.css';
import NProgress from "nprogress"
// import 'nprogress/nprogress.css'
NProgress.configure({ showSpinner: true });

createApp(App)
.use(store)
.use(router)
.use(Antd)
.use(Button)
.use(Form)
.use(Field)
.use(CellGroup)
.use(Cell)
.mount('#app')

