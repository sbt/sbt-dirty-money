sbt-dirty-money is an sbt plugin for cleaning Ivy2's cache. If you use `publishLocal` to test plugins and libraries, and you find yourself clearing Ivy2's cache often, this is a tool for you.

## setup

For sbt 1.x, add the following to `~/.sbt/1.0/plugins/dirtymoney.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-dirty-money" % "0.2.0")
```

and `~/.sbt/0.13/plugins/dirtymoney.sbt` for sbt 0.13:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-dirty-money" % "0.2.0")
```

Requires sbt 0.13.5+, 0.13.17+ recommend.

## how to use

### cleaning built artifacts

The above automatically adds 4 global tasks to sbt prompt: `cleanCacheFiles`, `cleanCache`, `cleanLocalFiles`, and `cleanLocal`.

To display what `cleanCache` would clean, run:

    > show cleanCacheFiles
    [info] * /Users/foo/.ivy2/cache/scala_2.9.1/sbt_0.11.0/org.scalaxb/sbt-scalaxb
    [info] * /Users/foo/.ivy2/cache/scala_2.9.1/sbt_0.11.0/org.scalaxb/sbt-scalaxb/jars/sbt-scalaxb-0.6.6-SNAPSHOT.jar

**NOTE**: This is calculated as `((dir / "cache") ** ("*" + organization + "*") ** ("*" + moduleName + "*")).get` where dir is `~/.ivy2`. **If there are related projects that include both your `organization` and `moduleName`, they would also be cleaned from the cache!** (For example, `unfiltered/unfiltered` would pick up any `unfiltered-xxx`). To delete the files, run:

    > cleanCache

Similarly, to display what `cleanLocal` would clean, run:

    > show cleanLocalFiles
    [info] * /Users/foo/.ivy2/local/org.scalaxb ...

This is calculated as `((dir / "local") ** ("*" + organization + "*") ** ("*" + moduleName + "*")).get`. To delete these files, run:

    > cleanLocal

If you're cleaning local, it's probably a good idea to clean cache too.

### cleaning other artifacts (0.1+ only)

By passing command arguments, you can target other projects as well.

To clean all artifacts that includes both `"net.databinder.dispatch"` and `"dispatch-json4s"` that are cached:

    > show cleanCacheFiles "net.databinder.dispatch" % "dispatch-json4s"
    [info] ArrayBuffer(~/.ivy2/cache/net.databinder.dispatch/dispatch-json4s-native_2.10, ...
    > cleanCache "net.databinder.dispatch" % "dispatch-json4s"
    [success] Total time: 0 s, completed Aug 21, 2013 9:47:26 PM

To clean all artifacts from the organization `"net.databinder.dispatch"` that are cached.

    > show cleanCacheFiles "net.databinder.dispatch"
    [info] ArrayBuffer(~/.ivy2/cache/net.databinder.dispatch, ...
    > cleanCache "net.databinder.dispatch"
    [success] Total time: 1 s, completed Aug 21, 2013 9:49:04 PM

To clean all artifacts from the cache:

    > show cleanCacheFiles *
    [info] ArrayBuffer(~/.ivy2/cache, ~/.ivy2/cache/--compile-internal.xml ...
    > cleanCache *
    [success] Total time: 95 s, completed Aug 21, 2013 9:51:17 PM

The arguments work the same for `cleanLocalFiles` and `cleanLocal`.

## License

MIT License. It's already in the license, but THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
Seriously, check what you're about to delete, and use it at your own risk.
