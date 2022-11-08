import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class CaseTest {

    @Test
    public void testcase2() {
        boolean test = false;
        Assertions.assertThat(test).isEqualTo(true);

    }
    @Test
    public void test_case1(){
        boolean test = false;
        Assertions.assertThat(test).isEqualTo(false);
    }




}
