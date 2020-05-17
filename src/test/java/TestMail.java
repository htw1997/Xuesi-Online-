import org.junit.Test;
import com.xuesi.utils.MailUtil;
import com.xuesi.utils.VerificationCodeUtils;

public class TestMail {
    @Test
    public void testMail() throws Exception {
        String code = VerificationCodeUtils.getRandomCode(6);
        MailUtil.sendEmail("1195261106@qq.com","验证码：" + code + "，请在30分钟内输入。","Y先生教育验证码");
    }
}
