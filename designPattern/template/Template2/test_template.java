abstract class DeployFlow {
    //使用final关键字来约束步骤不能轻易修改
    public final void buildFlow() {
        pullCodeFromGitlab(); //从GitLab上拉取代码
        compileAndPackage();  //编译打包
        copyToTestServer();   //部署测试环境
        testing();            //测试
        copyToRemoteServer(); //上传包到线上环境
        startApp();           //启动程序
    }
    public abstract void pullCodeFromGitlab();
    public abstract void compileAndPackage();
    public abstract void copyToTestServer();
    public abstract void testing();
    private void copyToRemoteServer() {
        System.out.println("统一自动上传 启动App包到对应线上服务器");
    }
    private void startApp() {
        System.out.println("统一自动 启动线上App");
    }
}
class LocalDeployFlow extends DeployFlow{
    @Override
    public void pullCodeFromGitlab() {
        System.out.println("手动将代码拉取到本地电脑......");
    }
    @Override
    public void compileAndPackage() {
        System.out.println("在本地电脑上手动执行编译打包......");
    }
    @Override
    public void copyToTestServer() {
        System.out.println("手动通过 SSH 上传包到本地的测试服务......");
    }
    @Override
    public void testing() {
        System.out.println("执行手工测试......");
    }
}
class CicdDeployFlow extends DeployFlow{
    @Override
    public void pullCodeFromGitlab() {
        System.out.println("持续集成服务器将代码拉取到节点服务器上......");
    }
    @Override
    public void compileAndPackage() {
        System.out.println("自动进行编译&打包......");
    }
    @Override
    public void copyToTestServer() {
        System.out.println("自动将包拷贝到测试环境服务器......");
    }
    @Override
    public void testing() {
        System.out.println("执行自动化测试......");
    }
}
class Client {
    public static void main(String[] args) {
        System.out.println("开始本地手动发布流程======");
        DeployFlow localDeployFlow = new LocalDeployFlow();
        localDeployFlow.buildFlow();
        System.out.println("********************");
        System.out.println("开始 CICD 发布流程======");

        DeployFlow cicdDeployFlow = new CicdDeployFlow();
        cicdDeployFlow.buildFlow();
    }
}
