# User Stories
 - User can play the game and register score 
 - User can quit the game at anytime
 - User can learn more in the information page:
   - How to play
   - View company profiles
   - View the current leaderboard
 
Long 
- Stock price = $10
- buy 1 shares at $10/stock
- total cost = $10
- Market:
  - goes down - stock price $8
  - My initial $10 decrease in value to $8

Short
 - Stock price $10
 - Short 1 shares at $10/stock
 - total cost = $10
 - Market:
   - goes down - stock price $8
     - My initial $10 increases to $12
   - goes up - stock price $12
     - My initial $10 decrease to $8


# UML
## Models
### Game
    - int gameId
    - int userId
    - int lastYear - round number (10 rounds per game)
    - int currentMarketId
### User
    - int userId
    - String username 
    - String password
- Spring Security LMS
### Company
    - int companyId
    - String Name
    - int companyTypeId
    - int defaultStockPrice
    - String risk
        (Stock)
    - int stockTotal - total stocks available (start at 100)
    - int dividend - a value a user gets per stock, per comapny, per ye
### Market
    - int companyId
    - int Price - stock price, starts at 50
    - int YearNumber
    - int MarketId
    - int gameId
    - int StockPurchased
    - Boolean isLongInvestment - position (short or long)
### MarketType
    - int roll - 1-20 random
    - int bullModify
    - int bearModify
    - int companyId
### Leaderboard
    - String username
    - int score

## Repository
### MarketTypeRepository
     int findRoll (int roll, int companyId, Boolean isBull)

### CompanyRepository
    List<Company> findAll()
    Company findById (int companyId)
    int findCompanyPriceById(int companyId)

### CompanyTypeRepository   
    String findCompanyTypeById (int companyTypeId)

### GameRepository
    Game findGameByUserID (int userId)
    Boolean addGameState (Game game)
    Boolean updateGameState (Game game)
    Boolean deleteGameState (int gameId)
[comment]: <> (Table needs UserId, CurrentMarket, yearNumber)

### UserRepository
    User FindByUserName (String Username)
    Boolean addUser (String username)
    Boolean deleteUser (userId)

### LeaderboardRepository
    List<LeaderBoard> findAll()
    Boolean addHighScore(String username, int score)

### MarketRepository   
    List<Market> findByGameId(int gameId)
    List<Market> findPortfolio(int gameId, int yearNumber)
    List<Market> findByCompanyId (int companyId, int gameId)
    boolean addMarket (Market market)
    boolean setBankrupt(boolean bankrupt, int gameId) - handle checking removal of a comapny in the market?
    boolean deleteMarket (int gameId) - remove company for bankruptcy
    boolean addCompany (int companyId, int gameId) - adds if less than 10 companies in market

## Service

### MarketTypeService
     int findRoll (int roll, int companyId, Boolean isBull)

### CompanyService
    List<Company> findAll()
    Company findById (int companyId)
    int readCompanyPriceById(int companyId)

### CompanyTypeService
    String findCompanyTypeById (int companyTypeId)

### GameService
    Game findGameByUserID (int UserId)
    Boolean addGameState (Game game)
    Boolean updateGameState (Game game)
    Boolean deleteGameState (int userId)
        - Boolean Verify (Game game)
[comment]: <> (Table needs UserId, yearNumber)
[comment]: <> (Everything above needs to be filled ei String username, Market market, int yearNumber)

### UserService
    User findByUserName (String Username)
    Boolean addUser (String username, HashedPassword)
    Boolean deleteUser (int userId)

### LeaderboardService
    List<Leaderboard> findAll()
    Boolean addHighScore(String username, int Score)

### MarketService
    List<Market> findByGameId(int gameId)
    List<Market> findPortfolio(int gameId, int yearNumber)
    List<Market> findByCompanyId (int companyId, int gameId)
    Boolean addMarket (Market market)
    Boolean setBankrupt(boolean bankrupt) - handle checking removal of a comapny in the market?
    Boolean deleteMarket (userId) - remove company for bankruptcy
    Boolean addCompany (int companyId) - adds if less than 10 companies in market

## Controller
### MarketTypeController
    @GETMAPPING
    int findRoll()

### CompanyController
    @GETMAPPING
    List<Company> findAll()
    @GETMAPPING
    Company findById (int companyId)
    @GETMAPPING
    int readCompanyPriceById (int companyId)
    @GETMAPPING
    int findStockAvailable (int companyId)

### CompanyTypeController
    @GETMAPPING
    String findCompanyTypeById (companyTypeId)

### GameController
    @GETMAPPING
    Game findGameByUserID (UserId)
    @POSTMAPPING
    Boolean addGameState (Game game)
    @PUTMAPPING
    Boolean updateGameState (Game game)
    @PUTMAPPING
    Boolean quitGame (Game game)
[comment]: <> (Table needs UserId, PortfolioId, Market, MarketType, yearNumber)

### UserController
    @GETMAPPING
    User findByUserName (String Username)
    @POSTMAPPING
    Boolean addUser (String username, HashedPassword)
    @DELETEMAPPING
    Boolean deleteUser (userId)

### LeaderboardController
    @GETMAPPING
    List<Leaderboard> findAll()
    @POSTMAPPING
    Boolean addHighScore(String username, int score)

### MarketController
    @GETMAPPING
    List<Market> findAll(int gameId)
    @GETMAPPING
    Market findByGameId (int gameId)
    @GETMAPPING
    List<Market> findPortfolio (int yearNumber, int GameId)
    @GETMAPPING
    List<Market>findByCompanyId (int companyId, int gameId) 
    @POSTMAPPING
    boolean addMarket (Market market)
    @PUTMAPPING
    boolean setBankrupt(boolean bankrupt)
    @DELETEMAPPING
    boolean deleteMarket (userId)
    @POSTMAPPING
    boolean addCompany (int companyId)

### Play
- Button audio toggle

 - createPortfolio
   - Input Companies, stock #/ company
   - Cancel() - Link to playMenu
   - Output Portfolio Object

 - playMenu
   - View ("Input" Game.Portfolio.companies) "Output" Display Portfolio
   - View ("Input" Game.Market.companies) "Output" Display Market

 - View
   - Return Table of Companies

### Information
 - ViewLeaderboard
 - ViewTutorial
 - ViewCompanies
 - Bug Report 

### ViewLeaderboard
    - Table Ranks
### ViewTutorial
    - Hard Code Tutorial
### ViewCompanies
    - Table Companies
### Bug Report
    - Inputs Username String, Subject String, Description String
    - Return Success/Failure

### Quit
    - Return to home

# WireFrames
    - See wireframe.jpgs

# Database Schema
     - See ERD diagram
