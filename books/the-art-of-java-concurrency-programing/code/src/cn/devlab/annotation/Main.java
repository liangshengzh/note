package cn.devlab.annotation;

import java.lang.reflect.Method;

/**
 * Created by zhong on 2016/12/24.
 */
public class Main {

    @AllowRoles(roles = {Role.AGGREGATION_ADMIN, Role.CATALOG_ADMIN})
    public static void execute(){

    }

    public static void main(String[] args) {

    }
}
