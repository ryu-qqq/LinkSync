### 명명 규칙(Naming Convention)

#### 1. **DTO 명명**
- 각 DTO는 그 **역할**에 따라 명확하게 구분되며, 사용 목적에 맞는 명칭을 사용한다.

##### - **저장용 DTO**:
- **생성(Create)** 또는 **수정(Update)**과 같은 명령성 작업을 처리하는 DTO.
- **예시**: `CreateBrandRequest`, `UpdateBrandRequest`, `CreateCategoryRequest`

##### - **조회용 DTO**:
- 데이터를 클라이언트에 반환할 때 사용하는 DTO. 보통 조회 결과를 담고 있으며, 응답 형식을 명확히 나타낸다.
- **예시**: `BrandResponse`, `CategoryResponse`, `BrandDetailResponse`

##### - **전달용 DTO**:
- 다른 서비스나 레이어 간에 데이터를 **전달**할 때 사용하는 객체. 데이터의 간략한 형태를 제공할 수 있다.
- **예시**: `BrandInfo`, `ProductBrandInfo`, `CategoryInfo`

#### 2. **서비스(Service) 명명**
- 서비스 클래스는 해당 서비스의 역할을 명확히 표현하며, **명령성 작업**과 **조회성 작업**을 구분한다.

##### - **Command 서비스**:
- 데이터를 생성, 수정, 삭제하는 작업을 처리하는 서비스.
- **예시**: `BrandCommandService`, `CategoryCommandService`

##### - **Query 서비스**:
- 데이터를 조회하거나 검색하는 작업을 처리하는 서비스.
- **예시**: `BrandQueryService`, `CategoryQueryService`

##### - **구현체 명명**:
- 기본적으로 `Impl` 접미사를 사용하여 구현체임을 나타낸다.
- **예시**: `BrandCommandServiceImpl`, `CategoryQueryServiceImpl`

#### 3. **레포지토리(Repository) 명명**
- **특정 기술**에 의존하는 레포지토리 클래스는 **구체적인 기술 이름**을 포함하여 명명한다. 이는 각 레포지토리의 구현 방식을 명확하게 구분하는 데 도움이 된다.

##### - **예시**:
- `BrandRepositoryMySQL`, `CategoryRepositoryElasticsearch`, `BrandRepositoryRedis`

#### 4. **패키지 구조 명명**
- **기능별로 패키지를 구분**하여 관리하며, 동일한 역할을 하는 클래스들은 함께 배치한다.

##### - **Command 패키지**:
- `com.example.project.module.brand.command`
- `com.example.project.module.category.command`

##### - **Query 패키지**:
- `com.example.project.module.brand.query`
- `com.example.project.module.category.query`

##### - **DTO 패키지**:
- `com.example.project.dto.brand`
- `com.example.project.dto.category`

##### - **Exception 패키지**:
- `com.example.project.exception`

#### 5. **Custom Validator 명명**
- 유효성 검증을 위한 커스텀 어노테이션과 Validator 클래스는 검증할 **대상 필드**와 관련성을 명확히 드러낸다.

##### - **예시**:
- `@LanguageCodeValidate`
- `LanguageCodeValidator`
