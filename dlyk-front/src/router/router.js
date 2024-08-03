import { createRouter, createWebHistory } from "vue-router";

//配置路由
const router = createRouter({
    //路由配置
    history:createWebHistory(),

    routes:[
        {
            path:'/',
            component : () => import('../view/DashboardView.vue'),
        }
    ]
})
export default router;
