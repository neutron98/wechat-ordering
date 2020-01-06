package cmu.youchun.recommender.common;

import lombok.Data;
@Data
public class CommonRes {

    //response status，"success" or "fail"
    private String status;

    //if status=success时，data = response JSON data
    //if status=fail，data will use a common error message
    private Object data;

    /**
     * A common method to return a common result
     * @param result
     * @return
     */
    public static CommonRes create(Object result){
        return CommonRes.create(result,"success");
    }

    /**
     * Return a common result, given a result object and status
     * @param result
     * @param status
     * @return
     */
    public static CommonRes create(Object result,String status){
        CommonRes commonRes = new CommonRes();
        commonRes.setStatus(status);
        commonRes.setData(result);
        return commonRes;
    }
}