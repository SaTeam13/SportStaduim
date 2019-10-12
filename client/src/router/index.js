import Vue from 'vue'
import router from 'vue-router';
import viewReservation from '../components/viewReservation';
import reserveTheField from '../components/ReserveTheField';
import Borrow from '../components/Borrow';
import Customer from '../components/Customer';
import viewCustomer from '../components/viewCustomer';
import Employee from '../components/Employee';
import PaymentMember from '../components/PaymentMember';
import viewPayment from '../components/viewPayment';
import Home from '../components/Home';
import viewEmployee from '../components/viewEmployee';
import viewBorrow from '../components/viewBorrow';
import sportEquipment from '../components/sportEquipment';
import viewSportEquipment from '../components/viewSportEquipment';
import Login from '../components/Login';
Vue.use(router);

export default new router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '/',
        component: Login
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/borrow',
            component: Borrow
        },
        {
            path: '/reservation',
            component: reserveTheField
        },
        {
            path: '/viewreserve',
            component: viewReservation
        },
        {
            path: '/customer',
            component: Customer
        },
        {
            path: '/viewcustomer',
            component: viewCustomer
        },
        {
            path: '/employee',
            component: Employee
        },
        {
            path: '/paymentmember',
            component: PaymentMember
        },
        {
            path: '/viewpayment',
            component: viewPayment
        },
        {
            path: '/viewemployee',
            component: viewEmployee
        },
        {
            path: '/viewborrow',
            component: viewBorrow
        },
        {
            path: '/sportequipment',
            component: sportEquipment
        },
        {
            path: '/viewsportequipment',
            component: viewSportEquipment
        },
        
        
    ]
});