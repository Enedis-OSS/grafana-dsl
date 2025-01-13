<!--
  ~ SPDX-FileCopyrightText: 2023-2025 Enedis
  ~
  ~ SPDX-License-Identifier: MIT
  ~
  -->

# Contributing

## Commit
When commiting, you have to add a trailer to your commit message as described [here](https://docs.gitlab.com/ee/user/project/changelogs.html#add-a-trailer-to-a-git-commit). It will be helpful for changelog automatic generation.  
Changelog trailer possible values are in [changelog_config.yml](.gitlab%2Fchangelog_config.yml)

## Release management
We are using semantic versioning. Tags version must be like x.y.z
### release flow
- create a new branch from main called release/x.y.z where x.y.z is the tag version to be created
- generate the changelog (Under test)
    - visit https://placide.enedis.fr/api/v4/projects/6822/repository/changelog?version=x.y.z
    - copy/paste the changelog in the `changelog.md` file. Review it if needed
- release using maven
```
    mvn versions:set -DnewVersion=x.y.z -DgenerateBackupPoms=false
    mvn versions:set-scm-tag -DnewTag=x.y.z -DgenerateBackupPoms=false
  ```
- push to remote release/x.y.z. Example of commit message : `release x.y.z`. Don't forget `--trailer "Changelog: release"`
- create PR to main
- review and merge the PR
- checkout the main. Don't forget to fetch & update local main from the remote main after PR merge
- create git tag
```
git tag x.y.z
git push origin x.y.z
```
- create a new branch and  prepare next dev version
```
mvn versions:set -DnewVersion=<NEXT_DEV_VERSION> -DgenerateBackupPoms=false
mvn versions:set-scm-tag -DnewTag=HEAD -DgenerateBackupPoms=false
```
- push the created branch. Example of commit message: chore: `prepare next development <NEXT_DEV_VERSION>`. Don't forget `--trailer "Changelog: release"`
- create a PR to the main branch
- review and merge it