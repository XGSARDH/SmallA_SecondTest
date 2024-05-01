package com.smalla.dao;

import com.smalla.po.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface AddressDao {
    /**
     * 根据用户ID来查找
     * @param userId
     * @return
     */
    List<Address> findByUserId(int userId);

    /**
     * 保存
     * @param address
     * @return
     * @throws SQLException
     */
    Integer save(Address address) throws SQLException;

    /**
     * 更新一个条目
     * @param address
     * @throws SQLException
     */
    void update(Address address) throws SQLException;

    /**
     * 删除一个条目
     * @param addressId
     * @throws SQLException
     */
    void delete(int addressId) throws SQLException;
}
