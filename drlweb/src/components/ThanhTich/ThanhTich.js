import React, { useContext, useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { authApi } from "../../configs/APIs";
import Table from 'react-bootstrap/Table';
import { Container } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import { MyUserContext } from '../../configs/Context';
import MySpinner from '../commons/MySpinner';

const ThanhTich = () => {
  const user = useContext(MyUserContext);
  const { hocKyId } = useParams();
  const [diemDieu1, setDiemDieu1] = useState(null);
  const [diemDieu2, setDiemDieu2] = useState(null);
  const [diemDieu3, setDiemDieu3] = useState(null);
  const [tongDiem, setTongDiem] = useState(null);
  const [xepLoai, setXepLoai] = useState(null);
  const [hoatDongDaThamGia, setHoatDongDaThamGia] = useState([]);
  const [diemHoatDong, setDiemHoatDong] = useState({});

  const loadData = async () => {
    try {
      if (user !== null) {
        const api = authApi();
        const response1 = await api.get(`/api/tongdiemdieu1/${hocKyId}`);
        const response2 = await api.get(`/api/tongdiemdieu2/${hocKyId}`);
        const response3 = await api.get(`/api/tongdiemdieu3/${hocKyId}`);
        const response4 = await api.get(`/api/tongdiem/${hocKyId}`);
        const response5 = await api.get(`/api/sinhvien/thanhtich/${hocKyId}`);
        const response6 = await api.get(`/api/hoatdongdathamgia/${hocKyId}/`);
  
        setDiemDieu1(response1.data);
        setDiemDieu2(response2.data);
        setDiemDieu3(response3.data);
        setTongDiem(response4.data);
        setXepLoai(response5.data.xepLoai);
        setHoatDongDaThamGia(response6.data);
  
        const diemHoatDongData = {};
        await Promise.all(
          response6.data.map(async (activity) => {
            const diemResponse = await authApi().get(`/api/diemhoatdong/${activity.id}`);
            diemHoatDongData[activity.id] = diemResponse.data;
          })
        );
        setDiemHoatDong(diemHoatDongData);
      }   
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  }

  useEffect(() => {
    loadData();
  }, [hocKyId]);

  if (user === null) {
    return <MySpinner animation="grow" variant="primary" />;
  }

  return (
    <div className='mb-4 mt-4'>
      <h1 className="text-center text-dark mb-4">Bảng Điểm Thành Tích</h1>
      <Container className='mt-2 mb-2'>
        <Table striped bordered>
          <thead>
            <tr>
              <th style={{ width: '5%' }}>STT</th>
              <th style={{ textAlign: 'center' }}>Minh chứng</th>
              <th style={{ textAlign: 'center' }}>Điểm SV</th>
              <th style={{ textAlign: 'center' }}>Điểm tối đa</th>
            </tr>
          </thead>

          <tbody>
            <tr>
              <td colSpan="2">
                <strong>Điều 1: Đánh giá về ý thức học tập</strong>
              </td>
              <td>{diemDieu1}</td>
              <td>
                20
              </td>
            </tr>

            {hoatDongDaThamGia
              .filter((hoatdong) => hoatdong.tieuchi === "Dieu 1")
              .map((hoatdong, index) => (

                <React.Fragment key={index}>
                  <tr>
                    <td colSpan="3"><strong>{hoatdong.tenhoatdong}</strong></td>
                    <td>{hoatdong.diem}</td>
                  </tr>
                  <tr>
                    <td>{index + 1}</td>
                    <td>
                      {diemHoatDong[hoatdong.id] > 0 ? (
                        <span className="text-success">Tham gia</span>
                      ) : (
                        <span className="text-danger">Không tham gia</span>
                      )}
                    </td>
                    <td>{diemHoatDong[hoatdong.id]}</td>
                    <td></td>
                  </tr>
                </React.Fragment>
              ))}


            <tr>
              <td colSpan="2">
                <strong>
                  Điều 2: Đánh giá về ý thức, kết quả chấp hành nội quy, quy định của nhà trường
                </strong>
              </td>
              <td>{diemDieu2}</td>
              <td>
                40
              </td>
            </tr>

            {hoatDongDaThamGia
              .filter((hoatdong) => hoatdong.tieuchi === "Dieu 2")
              .map((hoatdong, index) => (

                <React.Fragment key={index}>
                  <tr>
                    {/* <td>{index + 1}</td> */}
                    <td colSpan="3"><strong>{hoatdong.tenhoatdong}</strong></td>
                    <td>{hoatdong.diem}</td>
                  </tr>
                  <tr>
                    <td>{index + 1}</td>
                    <td>
                      {diemHoatDong[hoatdong.id] > 0 ? (
                        <span className="text-success">Tham gia</span>
                      ) : (
                        <span className="text-danger">Không tham gia</span>
                      )}
                    </td>
                    <td>{diemHoatDong[hoatdong.id]}</td>
                    <td></td>
                  </tr>
                </React.Fragment>
              ))}

            <tr>
              <td colSpan="2">
                <strong>
                  Điều 3: Đánh giá về ý thức và kết quả tham gia các hoạt động chính trị - xã hội, văn hóa, văn nghệ, thể thao, phòng chống các tệ nạn xã hội.
                </strong>
              </td>
              <td>{diemDieu3}</td>
              <td>
                40
              </td>
            </tr>

            {hoatDongDaThamGia
              .filter((hoatdong) => hoatdong.tieuchi === "Dieu 3")
              .map((hoatdong, index) => (

                <React.Fragment key={index}>
                  <tr>
                    {/* <td>{index + 1}</td> */}
                    <td colSpan="3"><strong>{hoatdong.tenhoatdong}</strong></td>
                    <td>{hoatdong.diem}</td>
                  </tr>
                  <tr>
                    <td>{index + 1}</td>
                    <td>
                      {diemHoatDong[hoatdong.id] > 0 ? (
                        <span className="text-success">Tham gia</span>
                      ) : (
                        <span className="text-danger">Không tham gia</span>
                      )}
                    </td>
                    <td>{diemHoatDong[hoatdong.id]}</td>
                    <td></td>
                  </tr>
                </React.Fragment>
              ))}

            <tr>
              <td align="right" colSpan="2">
                Tổng cộng:
              </td>
              <td align="center" colSpan="2">{tongDiem}</td>
              <td></td>
            </tr>

            <tr>
              <td align="right" colSpan="2">
                Xếp loại:
              </td>
              <td align="center" colSpan="2"><strong>{xepLoai}</strong></td>
            </tr>

          </tbody>

        </Table>

      </Container>
    </div>
  );
}

export default ThanhTich;
