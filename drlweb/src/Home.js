import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/commons/Header";
import Footer from "./components/commons/Footer";
import Login from "./components/User/Login";
import UserDetails from "./components/User/UserDetails";
import 'bootstrap/dist/css/bootstrap.min.css';
import { MyDispatchContext, MyUserContext } from "./configs/Context";
import { useReducer } from "react";
import { MyUserReducer } from "./configs/Reducers";
import Register from "./components/User/Register";
import ActivityDetails from "./components/HoatDong/ActivityDetails";
import RegisteredActivities from "./components/HoatDong/RegisteredActivities";
import ParticipatedActivities from "./components/HoatDong/ParticipatedActivities";
import ActivityHomeList from "./components/HoatDong/ActivityHomeList";
import ClearCookieOnMount from "./components/commons/ClearCookieOnMount";
import ChatBox from "./components/ChatBox/ChatBox";
import ThanhTich from "./components/ThanhTich/ThanhTich";
// import { Container } from "react-bootstrap";


const Home = () => {

  const [user, useDispatch] = useReducer(MyUserReducer, null);

  return (
    <BrowserRouter>
     <MyUserContext.Provider value={user}>
     <MyDispatchContext.Provider value={useDispatch}> 
     <ClearCookieOnMount />
      <Header />
      {/* <Container style={{ background: "#f0f0f0" }}> */}
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/userinfo" element={<UserDetails />} />
        <Route path="/" element={<ActivityHomeList />} />
        <Route path="/activities/:activityId" element={<ActivityDetails />} />
        <Route path="/registered-activities" element={<RegisteredActivities />} />
        <Route path="/participated-activities" element={<ParticipatedActivities />} />
        <Route path="/diem-ren-luyen/:hocKyId" element={< ThanhTich/>} />
        <Route path="/chat" element={user ? <ChatBox user={user} /> : <Login />} />
      </Routes>
      {/* </Container> */}

      <Footer />    
    </MyDispatchContext.Provider>
    </MyUserContext.Provider>
    </BrowserRouter>
  );
}

export default Home;