import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout'

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: '/login',
        children: [
            {
                path: 'staff',
                name: 'Staff',
                component: () => import("@/views/Staff")
            },
            {
                path: 'enterprise',
                name: 'Enterprise',
                component: () => import('@/views/Enterprise')
            },
            {
                path: 'center',
                name: 'Center',
                component: () => import('@/views/Center')
            },
            {
                path: 'model',
                name: 'Model',
                component: () => import('@/views/Model')
            },
            {
                path: 'api',
                name: 'API',
                component: () => import('@/views/API')
            },
            {
                path: 'order',
                name: 'Order',
                component: () => import('@/views/Order')
            },
            {
                path: 'budget',
                name: 'Budget',
                component: () => import('@/views/Budget')
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/Login")
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
