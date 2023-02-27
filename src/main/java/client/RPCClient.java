package client;

import common.Blog;
import common.User;
import service.BlogService;
import service.UserService;

public class RPCClient {
    public static void main(String[] args) {
        RPCClientProxy rpcClientProxy = new RPCClientProxy("127.0.0.1",8899);
        UserService userService = rpcClientProxy.getProxy(UserService.class);

        User userByUserId = userService.getUserByUserId(10);
        System.out.println("id which get from service endpoint" + userByUserId);

        User user = User.builder().userName("LiMing").id(100).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("insert into service endpoint" + integer);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("blog which get from service endpoint" + blogById);
    }
}
