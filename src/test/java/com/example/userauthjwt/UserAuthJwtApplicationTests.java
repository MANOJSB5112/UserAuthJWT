package com.example.userauthjwt;//package com.example.userauthjwt;

//@SpringBootTest
//class UserAuthJwtApplicationTests {
//
//    @Autowired
//    private RolesRepository rolesRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    @Transactional
//    void shouldAddNewRoleToDB() {
//        Roles role = new Roles(); // Assuming Role instead of Roles for class name
//        role.setName("Customer");
//        role.setRoleType(RoleType.CUSTOMER);
//        rolesRepository.save(role);
//    }

//    @Autowired
//    private JpaRegisteredClientRepository registeredClientRepository;

//    @Test
//    @Commit
//    void storeRegisteredClientIntoDB() {
//                RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret("{noop}secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scope("ADMIN")
//                .scope("STUDENT")
//                .scope("MENTOR") // Role
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//                registeredClientRepository.save(oidcClient);
//
//    }




//}
