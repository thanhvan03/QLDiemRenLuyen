import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/QLDRL/';

export const endpoints = {

    ///Người Dùng
    'register': '/api/users/',
    'login': '/api/login/',
    'current-user': '/api/current-user/',
    'userbyusername': '/api/userinfo/',

    ///Hoạt Động
    'totalactive': '/api/activities/', //// Xem tất cả hoạt động
    'activities': '/api/activities/page', ///Xem cac hoat dong có phân trang
    'hockys': '/api/hocky/',
    'details': (activityId) => `/api/activities/${activityId}/`,
    'hocky': (hocKyId) => `/api/hocky/${hocKyId}/`,
    'registered-activities': '/api/hoatdongdadangky/',
    'participated-activities': '/api/hoatdongdathamgia/',
    'activities-hocky': (hocKyId) => `/api/activities-hocky/${hocKyId}/`,
    'registry': '/api/registry/', ///Dang Ky HoatDong

    ///Like 
    'like': '/api/like/',
    'removeLike': '/api/like/remove/',
    'checklike': (activityId) => `/api/like/${activityId}`,
    "countlike": (activityId) => `/api/like/count/${activityId}`,

    ///comments
    'comment': '/api/comment/',
    'getcomments': (activityId) => `/api/comments/page/${activityId}/`,
    'totalcomments': (activityId) => `/api/comments/${activityId}`,

    ///Thành tích

}   

// console.log(cookie.load('token'));

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load('token')
        }
    })
}

export default axios.create({
    baseURL: 'http://localhost:8080/QLDRL/'
});