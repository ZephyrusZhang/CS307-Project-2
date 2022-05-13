package edu.sustech.cs307.mapper;

import edu.sustech.cs307.entity.CenterRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zephyrus
 * @since 2022-05-08
 */
@Mapper
public interface CenterRecordMapper extends BaseMapper<CenterRecord> {

    @Insert("insert into center_record (supply_center_id, product_model_id, staff_id, date, purchase_price, quantity) " +
            "values (#{supplyCenterId}, #{productModelId}, #{staffId}, #{date}, #{purchasePrice}, #{quantity})")
    void addCenterRecord(CenterRecord centerRecord);

}
