// db.auth($MONGO_INITDB_ROOT_USERNAME, $MONGO_INITDB_ROOT_PASSWORD)
// db = db.getSiblingDB($MONGO_INITDB_DATABASE)
db.createUser(
    {
        user: "user2412",
        pwd: "abcde4321",
        roles: [
            {
                role: "readWrite",
                db: "sodium-consumption-calc"
            }
        ]
    }
)