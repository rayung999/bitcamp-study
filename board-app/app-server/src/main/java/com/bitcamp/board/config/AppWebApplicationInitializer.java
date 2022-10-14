package com.bitcamp.board.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.bitcamp.board.filter.AdminCheckFilter;
import com.bitcamp.board.filter.LoginCheckFilter;

// 서블릿 콘테이너 
// ===> SpringServletContainerInitializer.onStartup() 호출
//      ===> WebApplicationInitializer.onStartup() 호출
//          ===> AbstractContextLoaderInitializer 구현체의 onStartup() 호출 
//              ===> registerContextLoaderListner() 호출
//                  ===> createRootApplicationContext() 호출
//          ===> IoC 컨테이너, 프론트 컨트롤러, 필터 준비 
// 
public class AppWebApplicationInitializer extends AbstractDispatcherServletInitializer {

  // 수퍼클래스에서 ContextLoaderListner에서 사용할 Root IoC 컨테이너를 리턴한다.  
  @Override
  protected WebApplicationContext createRootApplicationContext() {
    return null; // 설정할 필요가 없다면 null을 리턴 
  }

  // 수퍼클래스에서 DispatcherServlet을 준비할 때 사용할 서블릿 이름을 리턴한다 . 
  @Override
  protected String getServletName() {
    return "app";
  }

  // 수퍼클래스에 DispatcherServlet을 준비할 때 사용할 IoC 컨테이너를 리턴한다.
  @Override
  protected WebApplicationContext createServletApplicationContext() {
    // 웹 관련 컴포넌트를 다룰 수 있는 기능이 포함된 스프링 IoC 컨테이너 준비
    AnnotationConfigWebApplicationContext iocContainer = 
        new AnnotationConfigWebApplicationContext();
    iocContainer.register(AppConfig.class);
    return iocContainer;
  }

  // 수퍼클래스에서 DispatcherServlet의 URL을 연결할 때 사용할 경로 리턴
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app/*"};
  }

  // 수퍼클래스에서 필터를 등록할 때 사용할 정보를 리턴한다.
  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {
        new CharacterEncodingFilter("UTF-8"),
        new AdminCheckFilter(),
        new LoginCheckFilter()
    };
  }

  @Override
  protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(
        System.getProperty("java.io.tmpdir"), // 클라이언트가 보낸 파일을 임시 저장할 디렉토리
        1024 * 1024 * 5, // 파일크기
        1024 * 1024 * 5 * 2, // 첨부파일을 포함한 전체 요청 데이터의 크기 
        1024 * 1024 // 첨부파일의 데이터를 일시적으로 보관할 버퍼 크기 
        ));
  }

}
