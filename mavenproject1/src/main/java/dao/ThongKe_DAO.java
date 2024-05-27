/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ThongKe_DAO extends Remote{
    public Map<String, Double> findNhanVienWithMaxDoanhThuMap(Date fromDate, Date toDate) throws RemoteException;
    
}
