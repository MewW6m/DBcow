package com.dbcow.util;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Component
public class ControllerUtil {

    public boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }

    public String getErrorPageContent(Integer statusCode, String errorMessage) {
        String errorPageContent = """
                				<!doctype html>
                <html xmlns:th="http://www.thymeleaf.org">
                <head>
                    <meta charset="UTF-8">
                    <title>Atomsツール</title>
                    <link rel="shortcut icon" href="/img/favicon.ico">
                    <link href="/css/lib/uikit.min.css" rel="stylesheet">
                    <link href="/css/lib/jquery-editable-select.min.css" rel="stylesheet">
                	<link href="/css/common/body.css" rel="stylesheet">
                	<script src="/js/lib/jquery-1.12.4.min.js"></script>
                	<script src="/js/lib/jquery-editable-select.min.js"></script>
                    <script src="/js/lib/uikit.min.js"></script>
                    <script src="/js/lib/uikit-icons.min.js"></script>
                <style>
                body { margin: 0; color: #000 !important; background: #fff!important; }
                                .next-error-h1 {
                                  border-right: 1px solid rgba(0, 0, 0, .3);
                                }

                                @media (prefers-color-scheme: dark) {
                                  /*body { color: #fff; background: #000; }*/
                                  .next-error-h1 {
                                    /*border-right: 1px solid rgba(255, 255, 255, .3);*/
                                    border-right: 1px solid rgba(0, 0, 0, .3);
                                  }
                                }
                		.next-error-h1 {
                		  display:inline-block;margin:0;margin-right:20px;padding:0 23px 0 0;font-size:24px;font-weight:500;vertical-align:top;line-height:49px;
                		}
                		#div1 {
                		  font-family:-apple-system, BlinkMacSystemFont, Roboto, &quot;Segoe UI&quot;, &quot;Fira Sans&quot;, Avenir, &quot;Helvetica Neue&quot;, &quot;Lucida Grande&quot;, sans-serif;height:100vh;text-align:center;display:flex;flex-direction:column;align-items:center;justify-content:center;
                		}
                		#div2 {
                		  display:inline-block;text-align:left;line-height:49px;height:49px;vertical-align:middle;
                		}
                		#h21 {
                		  font-size:14px;font-weight:normal;line-height:49px;margin:0;padding:0
                		}
                </style>
                </head>
                <body>
                <div id="__next" >
                  <div data-overlay-container="true" style="position: absolute;height: 100vh;width: 100vw; background: transparent;">
                    <div id="div1">
                      <div>
                        <h1 class="next-error-h1">
                            [[${statusCode}]]
                        </h1>
                        <div id="div2">
                          <h2 id="h21">
                            [[${errorMessage}]]
                          </h2>
                        </div>
                      </div>
                    </div>
                  </div>
                 </body></html>
                				""";
        errorPageContent = errorPageContent.replace("[[${statusCode}]]", String.valueOf(statusCode));
        errorPageContent = errorPageContent.replace("[[${errorMessage}]]", errorMessage);
        return errorPageContent;
    }
}
