package cn.ck.mapper;

import cn.ck.entity.Studio;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-09-21
 */
public interface StudioMapper extends BaseMapper<Studio> {
        public Studio selectByzzid(String zzid);
}
