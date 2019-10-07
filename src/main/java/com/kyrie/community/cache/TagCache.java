package com.kyrie.community.cache;

import com.kyrie.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 标签暂存类
 * @author kyrie
 * @date 2019/10/7 - 15:50
 */
public class TagCache {
    /**
     * 获取标签库
     * @return
     */
    public static List<TagDTO> getTags() {
        ArrayList<TagDTO> tagList = new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript" ,"php" ,"css" ,"html" ,"html5" ,"java" ,"node.js" ,"python" ,
                "c++" ,"c" ,"golang" , "objective-c" ,"typescript" ,"shell", "c#" ,"swift" ,"sass" ,"bash" ,"ruby" ,
                "less" ,"asp.net" ,"lua scala" ,"coffeescript" ,"actionscript" ,"rust" ,"erlang" ,"perl"));
        tagList.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","express","django","flask","yii","ruby-on-rails tornado","koa","struts"));
        tagList.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu","centos","缓存","tomcat","负载均衡","unix","hadoop","windows-server"));
        tagList.add(server);

        TagDTO database = new TagDTO();
        database.setCategoryName("数据库和缓存");
        database.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql","memcached","sqlserver","postgresql","sqlite"));
        tagList.add(database);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagList.add(tool);

        return tagList;
    }

    /**
     * 判断是否有非法标签输入
     * @param tags
     * @return
     */
    public static String isInvalid(String tags) {
        // 1、先将标签字符串切割，得到数组
        String[] splitTags = tags.split(",");
        // 2、得到全部标签
        List<TagDTO> tagDTOS = getTags();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        // 3、判断输入标签是否存在
        String invalidTag = Arrays.stream(splitTags).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));

        return invalidTag;
    }
}
