package com.tufei.architecturedemo.data.bean;

/**
 *
 * @author tufei
 * @date 2017/7/30
 */

public class AccessToken {

    /**
     * access_token : 24.0cf6c695377ef433fe2fc30e690a0f38.2592000.1503110467.282335-9903858
     * session_key : 9mzdC3ssaSjKFiSMon08UhOvsWSYbaRUvq53jhyHHZ4QEI43Bw9nPMTC0UASz63zDYSp/azWZY3SU8JLPuyENYSy7REK
     * scope : public vis-faceverify_faceverify vis-faceattribute_faceattribute vis-faceverify_faceverify_v2 brain_all_scope wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian wangrantest_test wangrantest_test1 bnstest_test1 bnstest_test2 vis-classify_flower
     * refresh_token : 25.07c74b461feecdabe98366152a54bdef.315360000.1815878467.282335-9903858
     * session_secret : 37343d1796791c4608516facf18db4d1
     * expires_in : 2592000
     */

    private String access_token;
    private String session_key;
    private String scope;
    private String refresh_token;
    private String session_secret;
    private int expires_in;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
