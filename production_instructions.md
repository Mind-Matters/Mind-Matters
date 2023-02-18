# Updating production:
1. Log into your server 
   - ssh -i .ssh/codeup-aws.pem ubuntu@18.118.217.74
2. cd Mind-Matters
3. git pull origin main
4. pm2 reload all
5. pm2 logs

# Manage databases:
1. open src/main/resources/application.properties
2. top section connects to local MySQL, bottom section connects to production db
3. 